import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SuppliersFormComponent } from './components/suppliers/suppliers-form/suppliers-form.component';
import { SuppliersListComponent } from './components/suppliers/suppliers-list/suppliers-list.component';
import { SuppliersViewComponent } from './components/suppliers/suppliers-view/suppliers-view.component';

import { ProductsFormComponent } from './components/products/products-form/products-form.component';
import { ProductsListComponent } from './components/products/products-list/products-list.component';
import { ProductsViewComponent } from './components/products/products-view/products-view.component';

import { OrdersViewComponent } from './components/orders/orders-view/orders-view.component';
import { OrdersFormComponent } from './components/orders/orders-form/orders-form.component';
import { OrdersListComponent } from './components/orders/orders-list/orders-list.component';

import { HomeComponent } from './components/home/home.component';

import { NotFoundComponent } from './components/not-found/not-found.component';

import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent }
  ,
  { path: 'suppliers', component: SuppliersListComponent },
  { path: 'suppliers/form/:id-supplier', component: SuppliersFormComponent },
  { path: 'suppliers/form', pathMatch: "full", component: SuppliersFormComponent },
  { path: 'suppliers/:id-supplier', component: SuppliersViewComponent }
  ,
  { path: 'products', component: ProductsListComponent },
  { path: 'products/form/:id-product', component: ProductsFormComponent },
  { path: 'products/form', pathMatch: "full", component: ProductsFormComponent },
  { path: 'products/:id-product', component: ProductsViewComponent }
  ,
  { path: 'orders', component: OrdersListComponent },
  { path: 'orders/form/:id-order', component: OrdersFormComponent },
  { path: 'orders/form', pathMatch: "full", component: OrdersFormComponent },
  { path: 'orders/:id-order', component: OrdersViewComponent }
  ,
  { path: '404-notfound', component: NotFoundComponent },
  {path:'login',component:LoginComponent},
  {path:'', pathMatch:'full', redirectTo:'home'},
  { path: '**', redirectTo: '404-notfound' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

