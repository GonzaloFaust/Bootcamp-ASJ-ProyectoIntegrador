import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
//import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faPen } from '@fortawesome/free-solid-svg-icons'
import { CategoriesService } from 'src/app/services/categories.service';
import { Category } from 'src/app/models/category';
import { HttpResponse } from '@angular/common/http';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {
  faEdit = faPen
  constructor(private productsService: ProductsService, private categoryService: CategoriesService) { }
  ngOnInit(): void {
    this.productsService.getProducts().subscribe(
      {
        next: (data: HttpResponse<Product[]>) => { this.products = data.body! },
        error: (error) => console.log(error)
      }
    )
    this.categoryService.getCategories().subscribe({

      next: (data: HttpResponse<Category[]>) => { this.categories = data.body! },
      error: (error) => console.log(error)
    })
  }
  categories: Category[] = []
  products: Product[] = []
}
