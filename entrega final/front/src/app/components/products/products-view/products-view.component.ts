import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductsService } from 'src/app/services/products.service';
import {SuppliersService} from 'src/app/services/suppliers.service';
@Component({
  selector: 'app-products-view',
  templateUrl: './products-view.component.html',
  styleUrls: ['./products-view.component.css']
})
export class ProductsViewComponent implements OnInit{

  constructor(public productoService: ProductsService, public provServ: SuppliersService, private ruta: ActivatedRoute, private router:Router) { }
   product:Product | undefined;
   razonSocial:string="";

  ngOnInit(): void {
    this.productoService.getProductById(this.ruta.snapshot.paramMap.get("id-producto")!).subscribe(
      {
        next:(data:HttpResponse<Product>)=>{this.product=data.body!} ,
          error: (error:any)=> console.log(error)
      }
    )
    // this.razonSocial = this.provServ.getSupplierById(this.producto.cod_proveedor)?.razon_social
  }
  
  deleteProducto() {
    // this.productoService.deleteProduct()
    setTimeout(()=>this.router.navigateByUrl('/suppliers'),1000)
  }
}





