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
    "prodAvailable": true
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

    this.categoryService.getCategories().subscribe(
      {
        next: (data: HttpResponse<Category[]>) => { this.categories = data.body! },
        error: (error: any) => console.log(error)
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

  createProduct(form: NgForm) {

    this.productsService.addProduct(this.product).subscribe(data => console.log(data))
    // setTimeout(()=>this.router.navigateByUrl('/productos'),1000)
  }

}
