import { Component, OnInit } from '@angular/core';
import { NgForm, FormControl} from '@angular/forms';
import { OrdenesService, blankOrder } from 'src/app/services/ordenes.service';
import { ProveedoresService } from 'src/app/services/proveedores.service';
import { ProductosService } from 'src/app/services/productos.service';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderState } from 'src/app/models/orderState';

@Component({
  selector: 'app-ordenes-crear',
  templateUrl: './ordenes-crear.component.html',
  styleUrls: ['./ordenes-crear.component.css']
})
export class OrdenesCrearComponent implements OnInit {

  idParam = this.route.snapshot.paramMap.get("id-orden");
  isEditSession: boolean = this.idParam !== null;

  proveedores = this.provService.getProveedores().map(prov => { return { cod_proveedor: prov.codigo, razon_social: prov.razon_social } })

  productosAgregados: any[] = []

  estadosOrden = Object.values(OrderState)

  productosDisponibles: any[] = []

  minFechaEmision: string = '2000-01-01'; 
  maxFechaActual: string = new Date().toISOString().split('T')[0]; 
  estadoOrden: FormControl=new FormControl()

  constructor(private route: ActivatedRoute, public service: OrdenesService, private provService: ProveedoresService, private productService: ProductosService, private router:Router) { }

  ngOnInit(): void {

    if (this.isEditSession) {
      this.service.getOrdenById(this.idParam!);
      this.updateProductosAgregados()
    }
    else {
      this.service.orderTemplate = structuredClone(blankOrder)
      this.cleanTemplate();
    }
  }

  createOrden(form: NgForm) {
    if (this.isEditSession) this.service.editOrdenCompra(this.idParam!)
    else this.service.addOrden()
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
    this.productosDisponibles = this.productService.getProductosbyProveedor(this.service.orderTemplate.cod_proveedor)
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
          ...this.productService.getProductoById(this.service.orderTemplate.cod_proveedor + p.codigo_SKU),
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
