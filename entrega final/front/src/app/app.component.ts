import { Component } from '@angular/core';
import { ProductsListComponent } from './components/productos/products-list/products-list.component';
import { SuppliersListComponent } from './components/suppliers/suppliers-list/suppliers-list.component';
import { OrdersListComponent } from './components/ordenes/orders-list/orders-list.component';
import { SuppliersFormComponent } from './components/suppliers/suppliers-form/suppliers-form.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'proyecto-integrador';
}
