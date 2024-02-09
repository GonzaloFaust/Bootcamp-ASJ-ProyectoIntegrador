import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductsService } from 'src/app/services/products.service';
import {SuppliersService} from 'src/app/services/suppliers.service';
import { environment } from 'src/environments/environment.development';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-products-view',
  templateUrl: './products-view.component.html',
  styleUrls: ['./products-view.component.css']
})
export class ProductsViewComponent implements OnInit{

  constructor(public productoService: ProductsService, public provServ: SuppliersService, private ruta: ActivatedRoute, private router:Router) { }
   product:any = {
    "prodSku": "",
    "supplier": {
      "supId": '',
    },
    "category": {
      "catId": ''
      },
    "prodImage": "",
    "prodName": "",
    "prodDescription": "",
    "prodPrice": '',
    "prodAvailable":true
  };
   razonSocial:string="";

  ngOnInit(): void {
    this.productoService.getProductById(this.ruta.snapshot.paramMap.get("id-product")!).subscribe(
      {
        next:(data:HttpResponse<Product>)=>{this.product=data.body!} ,
          error: (error:any)=> console.log(error)
      }
    )
    // this.razonSocial = this.provServ.getSupplierById(this.producto.cod_proveedor)?.razon_social
  }
  handleImageError(image: HTMLImageElement) {
    image.src = environment.PROD_IMAGE_MOCK
  }
  deleteProduct() {
    Swal.fire({
      title: 'Eliminar producto',
      text: `Seguro desea eliminar ${this.product.prodName}?`,
      icon: 'question',
      showCancelButton: true,
      allowOutsideClick: false,
      allowEscapeKey: false,
      confirmButtonText: 'Eliminar',
      confirmButtonColor: "#b11",
      cancelButtonText: 'Cancelar',
    })
      .then(res => {
        if (res.isConfirmed) {
          this.productoService.deleteProduct(this.product).subscribe(
            {
              next: (data) => { 
  
                  Swal.fire({
                    position: "center",
                    icon: "success",
                    title: `producto ${this.product.prodName} eliminado correctamente`,
                    showConfirmButton: false,
                    timer: 1500
                  });
                  setTimeout(()=>this.router.navigateByUrl('/products'),2000)
              },
              error: (error) => {
                Swal.fire({
                  position: "center",
                  icon: "success",
                  title: error.error,
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            }
          )
        }
      })
    // 
  }
    
  }






