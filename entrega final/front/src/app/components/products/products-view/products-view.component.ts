import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { Supplier } from 'src/app/models/supplier';
import { ProductsService, blankProduct } from 'src/app/services/products.service';
import {SuppliersService, blankProvider } from 'src/app/services/suppliers.service';
@Component({
  selector: 'app-products-view',
  templateUrl: './products-view.component.html',
  styleUrls: ['./products-view.component.css']
})
export class ProductsViewComponent implements OnInit{

  constructor(public productoService: ProductsService, public provServ: SuppliersService, private ruta: ActivatedRoute, private router:Router) { }
   producto:Product=blankProduct;
   razonSocial:string="";

  ngOnInit(): void {
    this.producto = this.productoService.getProductById(this.ruta.snapshot.paramMap.get("id-producto")!)
    this.razonSocial = this.provServ.getSupplierById(this.producto.cod_proveedor)?.razon_social
  }
  
  deleteProducto() {
    this.productoService.deleteProduct()
    setTimeout(()=>this.router.navigateByUrl('/proveedores'),1000)
  }
}





