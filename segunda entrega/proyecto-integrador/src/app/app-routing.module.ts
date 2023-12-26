import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProveedoresCrearComponent } from './components/proveedores/proveedores-crear/proveedores-crear.component';
import { ProveedoresListaComponent } from './components/proveedores/proveedores-lista/proveedores-lista.component';
import { ProveedoresVerComponent } from './components/proveedores/proveedores-ver/proveedores-ver.component';
import { ProductosCrearComponent } from './components/productos/productos-crear/productos-crear.component';
import { ProductosListaComponent } from './components/productos/productos-lista/productos-lista.component';
import { OrdenesCrearComponent } from './components/ordenes/ordenes-crear/ordenes-crear.component';
import { OrdenesListaComponent } from './components/ordenes/ordenes-lista/ordenes-lista.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ProductosVerComponent } from './components/productos/productos-ver/productos-ver.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'proveedores', component: ProveedoresListaComponent },
  { path: 'proveedores/edit/:id-proveedor', component: ProveedoresCrearComponent },
  { path: 'proveedores/new', pathMatch: "full", component: ProveedoresCrearComponent },
  { path: 'proveedores/:id-proveedor', component: ProveedoresVerComponent },
  { path: 'productos', component: ProductosListaComponent },
  { path: 'productos/new', pathMatch: "full", component: ProductosCrearComponent },
  { path: 'productos/edit/:id-proveedor', component: ProductosCrearComponent },
  { path: 'productos/:id-producto', component: ProductosVerComponent }
  ,
  // {
  //   path: 'ordenes', component: OrdenesListaComponent,
  //   children: [
  //     { path: 'new', component: OrdenesCrearComponent },
  //     { path: 'edit', component: OrdenesCrearComponent }
  //   ]
  // },
  { path: '404-notfound', component: NotFoundComponent },
  { path: '**', redirectTo: '404-notfound' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

