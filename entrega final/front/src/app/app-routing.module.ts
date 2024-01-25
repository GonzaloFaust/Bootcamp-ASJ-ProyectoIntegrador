import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProveedoresCrearComponent } from './components/proveedores/proveedores-crear/proveedores-crear.component';
import { ProveedoresListaComponent } from './components/proveedores/proveedores-lista/proveedores-lista.component';
import { ProveedoresVerComponent } from './components/proveedores/proveedores-ver/proveedores-ver.component';

import { ProductosCrearComponent } from './components/productos/productos-crear/productos-crear.component';
import { ProductosListaComponent } from './components/productos/productos-lista/productos-lista.component';
import { ProductosVerComponent } from './components/productos/productos-ver/productos-ver.component';

import { OrdenesVerComponent } from './components/ordenes/ordenes-ver/ordenes-ver.component';
import { OrdenesCrearComponent } from './components/ordenes/ordenes-crear/ordenes-crear.component';
import { OrdenesListaComponent } from './components/ordenes/ordenes-lista/ordenes-lista.component';

import { HomeComponent } from './components/home/home.component';

import { NotFoundComponent } from './components/not-found/not-found.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent }
  ,
  { path: 'proveedores', component: ProveedoresListaComponent },
  { path: 'proveedores/edit/:id-proveedor', component: ProveedoresCrearComponent },
  { path: 'proveedores/new', pathMatch: "full", component: ProveedoresCrearComponent },
  { path: 'proveedores/:id-proveedor', component: ProveedoresVerComponent }
  ,
  { path: 'productos', component: ProductosListaComponent },
  { path: 'productos/edit/:id-producto', component: ProductosCrearComponent },
  { path: 'productos/new', pathMatch: "full", component: ProductosCrearComponent },
  { path: 'productos/:id-producto', component: ProductosVerComponent }
  ,
  { path: 'ordenes', component: OrdenesListaComponent },
  { path: 'ordenes/edit/:id-orden', component: OrdenesCrearComponent },
  { path: 'ordenes/new', pathMatch: "full", component: OrdenesCrearComponent },
  { path: 'ordenes/:id-orden', component: OrdenesVerComponent }
  ,
  { path: '404-notfound', component: NotFoundComponent },
  { path: '**', redirectTo: '404-notfound' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

