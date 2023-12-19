import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProveedoresCrearComponent } from './components/proveedores/proveedores-crear/proveedores-crear.component';
import { ProveedoresListaComponent } from './components/proveedores/proveedores-lista/proveedores-lista.component';
import { ProductosCrearComponent } from './components/productos/productos-crear/productos-crear.component';
import { ProductosListaComponent } from './components/productos/productos-lista/productos-lista.component';
import { OrdenesCrearComponent } from './components/ordenes/ordenes-crear/ordenes-crear.component';
import { OrdenesListaComponent } from './components/ordenes/ordenes-lista/ordenes-lista.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'proveedores', component: ProveedoresListaComponent },
  { path: 'proveedores/new', component: ProveedoresCrearComponent },
  { path: 'proveedores/edit/:idProveedor', component: ProveedoresCrearComponent }
  ,
  { path: 'productos', component: ProductosListaComponent },
  { path: 'productos/new', component: ProductosCrearComponent },
  { path: 'productos/edit/:idProveedor', component: ProductosCrearComponent }
,
  {
    path: 'ordenes', component: OrdenesListaComponent,
    children: [
      { path: 'new', component: OrdenesCrearComponent },
      { path: 'edit', component: OrdenesCrearComponent }
    ]
  },
  { path: '404-notfound', component: NotFoundComponent },
  { path: '**', redirectTo: '404-notfound' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

