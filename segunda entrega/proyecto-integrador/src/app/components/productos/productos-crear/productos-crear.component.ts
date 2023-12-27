import { Component, OnInit } from '@angular/core';
import { ProductosService, blankProduct } from 'src/app/services/productos.service';
import { ProveedoresService } from 'src/app/services/proveedores.service';
import { NgForm } from '@angular/forms';
import { productCategory } from 'src/app/models/productCategory';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-productos-crear',
  templateUrl: './productos-crear.component.html',
  styleUrls: ['./productos-crear.component.css']
})
export class ProductosCrearComponent implements OnInit {

  idParam = this.route.snapshot.paramMap.get("id-producto");

  isEditSession: boolean = this.idParam !== null;
  
  proveedores= this.servProv.getProveedores().map(prov=>{return {codigo:prov.codigo, razon_social:prov.razon_social}})
  categorias=Object.values(productCategory)

  constructor(private route:ActivatedRoute, public service:ProductosService, public servProv: ProveedoresService){}
  
  ngOnInit(): void {
    if (this.isEditSession){
      this.service.getProductoById(this.idParam!)
    }
    else{
      this.service.productTemplate= structuredClone(blankProduct)
    }
  }
  
  createProduct(form:NgForm){
    if (this.isEditSession) this.service.editProducto(this.idParam!)
    else this.service.addProducto(form)
  }
  
}
