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
  product: Product = {
    "prodId": 0,
    "prodSku": "",
    "supplier": {
      "supId": 0,
      "supCode": "",
      "supBussinessName": "",
      "field": {
        "fieldId": 0,
        "fieldName": "",
        "fieldDetail": ""
      },
      "supImage": "",
      "supWebsite": "",
      "supEmail": "",
      "supTelephone": "",
      "address": {
        "addrId": 0,
        "state": {
          "stateId": 0,
          "country": {
            "counId": 0,
            "counName": ""
          },
          "stateName": ""
        },
        "cityName": "",
        "addrPostcode": "",
        "addrStreet": "",
        "addrNumber": 0,
        "addrFloor": 0,
        "addrApartment": ""
      },
      "supCuit": "",
      "taxCond": {
        "taxId": 0,
        "taxCondTitle": ""
      },
      "supContact": {
        "supContactId": 0,
        "supContactName": "",
        "supContactLastname": "",
        "supContactTelephone": "",
        "supContactEmail": "",
        "supContactRole": "",
      },
      "isActive": true
    },
    "category": {
      "catId": 0,
      "catName": "",
      "catDetail": "",
      "field": {
        "fieldId": 0,
        "fieldName": "",
        "fieldDetail": ""
      },

    },
    "prodImage": "",
    "prodName": "",
    "prodDescription": "",
    "prodPrice": 0,
    "createdAt": "",
    "updatedAt": "",
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
