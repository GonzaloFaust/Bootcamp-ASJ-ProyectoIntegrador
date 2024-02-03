import { Component, OnInit } from '@angular/core';
import { NgForm, FormControl} from '@angular/forms';
import { OrdersService, blankOrder } from 'src/app/services/orders.service';
import { SuppliersService } from 'src/app/services/suppliers.service';
import { ProductsService } from 'src/app/services/products.service';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderState } from 'src/app/models/orderState';
import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';


const now = new Date();

@Component({
  selector: 'app-orders-form',
  templateUrl: './orders-form.component.html',
  styleUrls: ['./orders-form.component.css'],
})
export class OrdersFormComponent implements OnInit {


  model: NgbDateStruct | undefined;
  formattedDate: string | null | undefined;
  
  constructor(private route: ActivatedRoute, public service: OrdersService, private provService: SuppliersService, private productService: ProductsService, private router:Router) { }


 








  idParam = this.route.snapshot.paramMap.get("id-orden");
  isEditSession: boolean = this.idParam !== null;

  proveedores = this.provService.getSuppliers().map(prov => { return { cod_proveedor: prov.codigo, razon_social: prov.razon_social } })

  productosAgregados: any[] = []

  estadosOrden = Object.values(OrderState)

  productosDisponibles: any[] = []

  minFechaEmision: string = '2000-01-01'; 
  maxFechaActual: string = new Date().toISOString().split('T')[0]; 
  estadoOrden: FormControl=new FormControl()


  ngOnInit(): void {

    if (this.isEditSession) {
      this.service.getOrderById(this.idParam!);
      this.updateProductosAgregados()
    }
    else {
      this.service.orderTemplate = structuredClone(blankOrder)
      this.cleanTemplate();
    }
  }

  createOrden(form: NgForm) {
    if (this.isEditSession) this.service.editOrder(this.idParam!)
    else this.service.addOrder()
  for(let prov of this.proveedores){
    localStorage.removeItem(prov.cod_proveedor)
  }
    setTimeout(()=>this.router.navigateByUrl('/ordenes'),1000)
  }
  cancelarOrden(){
    this.service.orderTemplate.state=OrderState.cancelado
    setTimeout(()=>this.router.navigateByUrl('/ordenes'),1000)
  }

  cleanTemplate(){
    const l= this.service.orderTemplate.productos.length
    for(let i=0; i<l;i++){
      this.service.orderTemplate.productos.pop()
    }
    for(let j=0;j<this.productosAgregados.length;j++){
      this.service.orderTemplate.productos.push(this.productosAgregados[j])
    }
  }

  getProductosProveedor() {
    this.productosDisponibles = this.productService.getProductsbySupplier(this.service.orderTemplate.cod_proveedor)
    this.productosAgregados= JSON.parse(localStorage.getItem(this.service.orderTemplate.cod_proveedor)!) || []
    this.cleanTemplate();
    this.updateProductosAgregados()
  }

  agregarProducto(prod: any, quantity: any) {
    this.service.orderTemplate.productos.push({ codigo_SKU: prod, cantidad: quantity })
    this.updateProductosAgregados()
  }

  private updateProductosAgregados() {
    this.productosAgregados = this.service.orderTemplate.productos
      .map( p => {
        return {
          ...this.productService.getProductById(this.service.orderTemplate.cod_proveedor + p.codigo_SKU),
          cantidad: p.cantidad
        }
      })
      .filter( p => p.codigo_SKU!=="" && p.cantidad>0)
      localStorage.setItem(this.service.orderTemplate.cod_proveedor,JSON.stringify(this.productosAgregados))
  }

  deleteProduct(id:string){
    this.productosAgregados= this.productosAgregados.filter(p=>p.codigo_SKU!==id)
    this.cleanTemplate();
    this.updateProductosAgregados()
  }
}
