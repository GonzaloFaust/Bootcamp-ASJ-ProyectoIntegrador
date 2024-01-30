import { Component, OnInit } from '@angular/core';
import { ProductsService, blankProduct } from 'src/app/services/products.service';
import {  SuppliersService } from 'src/app/services/suppliers.service';
import { NgForm } from '@angular/forms';
import { productCategory } from 'src/app/models/productCategory';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-products-form',
  templateUrl: './products-form.component.html',
  styleUrls: ['./products-form.component.css']
})
export class ProductsFormComponent implements OnInit {

  idParam = this.route.snapshot.paramMap.get("id-producto");

  isEditSession: boolean = this.idParam !== null;
  
  proveedores= this.servProv.getSuppliers().map(prov=>{return {codigo:prov.codigo, razon_social:prov.razon_social}})
  categorias=Object.values(productCategory)

  constructor(private route:ActivatedRoute, public service:ProductsService, public servProv: SuppliersService, private router:Router){}
  
  ngOnInit(): void {
    if (this.isEditSession){
      this.service.getProductById(this.idParam!)
    }
    else{
      this.service.productTemplate= structuredClone(blankProduct)
    }
  }
  
  createProduct(form:NgForm){
    if (this.isEditSession) this.service.editProduct(this.idParam!)
    else this.service.addProduct(form)
    setTimeout(()=>this.router.navigateByUrl('/productos'),1000)
  }
  
}
