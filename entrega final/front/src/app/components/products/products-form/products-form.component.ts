import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
import { SuppliersService } from 'src/app/services/suppliers.service';
import { NgForm } from '@angular/forms';
import { Category } from 'src/app/models/category';
import { ActivatedRoute, Router } from '@angular/router';
import { Supplier } from 'src/app/models/supplier';
import { HttpResponse } from '@angular/common/http';
import { Product } from 'src/app/models/product';
import { CategoriesService } from 'src/app/services/categories.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-products-form',
  templateUrl: './products-form.component.html',
  styleUrls: ['./products-form.component.css']
})
export class ProductsFormComponent implements OnInit {

  idParam = this.route.snapshot.paramMap.get("id-product");

  isEditSession: boolean = this.idParam !== null;

  suppliers: Supplier[] = []
  categories: Category[] = [];
  product: any = {
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

  constructor(
    private route: ActivatedRoute,
    public productsService: ProductsService,
    public suppliersService: SuppliersService,
    private router: Router,
    private categoryService: CategoriesService
  ) { }

  ngOnInit(): void {
    this.suppliersService.getSuppliers().subscribe(
      {
        next: (data: HttpResponse<Supplier[]>) => { this.suppliers = data.body! },
        error: (error: any) => console.log("error aca guachin")
      }
      )

    //---------------------
    if (this.isEditSession) {
      this.productsService.getProductById(this.idParam!).subscribe(
        {
          next: (data: HttpResponse<Product>) => { this.product = data.body! },
          error: (error: any) => console.log("error aca guachin")
        }
      )
    }
  }

  getCategoriesOfSupplier(supplier:number){
  this.categoryService.getCategoriesBySupplier(supplier).subscribe(
    {
      next: (data) => { this.categories = data.body! },
      error: (error) => {
            console.log(error.error)
            Swal.fire({
              position: "center",
              icon: "error",
              title: error.error,
  
              showConfirmButton: false,
              timer: 1500
            })
          }
    }
  )
  }

  createProduct(form: NgForm) {

    if(this.isEditSession){
      this.productsService.editProduct(this.product).subscribe(
        {
          next: (data) => {

            Swal.fire({
              position: "center",
              icon: "success",
              title: `Producto ${this.product.prodName} modificado exitosamente`,
  
              showConfirmButton: false,
              timer: 1500
            })
            setTimeout(() => this.router.navigateByUrl('/products'), 1000)
          },
          error: (error) => {
            console.log(error.error)
            Swal.fire({
              position: "center",
              icon: "error",
              title: error.error,
  
              showConfirmButton: false,
              timer: 1500
            })
          }
        }
      )
    }
    else{

      this.productsService.addProduct(this.product).subscribe(
        {
          next: (data) => {
            Swal.fire({
              position: "center",
              icon: "success",
              title: `Producto ${this.product.prodName} creado exitosamente`,
  
              showConfirmButton: false,
              timer: 1500
            })
            setTimeout(() => this.router.navigateByUrl('/products'), 1000)
          },
          error: (error) => {
            console.log(error.error)
            Swal.fire({
              position: "center",
              icon: "error",
              title: error.error,
  
              showConfirmButton: false,
              timer: 1500
            })
          }
        }
      )
    }
    // setTimeout(()=>this.router.navigateByUrl('/productos'),1000)
  }

}
