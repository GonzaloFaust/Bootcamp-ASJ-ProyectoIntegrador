import { Component } from '@angular/core';
import { ProductosListaComponent } from './components/productos/productos-lista/productos-lista.component';
import { ProveedoresListaComponent } from './components/proveedores/proveedores-lista/proveedores-lista.component';
import { OrdenesListaComponent } from './components/ordenes/ordenes-lista/ordenes-lista.component';
import { ProveedoresCrearComponent } from './components/proveedores/proveedores-crear/proveedores-crear.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'proyecto-integrador';
}
