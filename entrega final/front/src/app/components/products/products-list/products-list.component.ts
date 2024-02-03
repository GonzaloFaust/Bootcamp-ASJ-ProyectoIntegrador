import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
//import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {faPen } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {
faEdit=faPen
  constructor(private productosServ: ProductsService) { }
  ngOnInit(): void {
    this.products=this.productosServ.getProducts()
    
  }

  products: any [] = []
}
