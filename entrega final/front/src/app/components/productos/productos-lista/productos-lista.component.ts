import { Component, OnInit } from '@angular/core';
import { ProductosService } from 'src/app/services/productos.service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {faPen } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-productos-lista',
  templateUrl: './productos-lista.component.html',
  styleUrls: ['./productos-lista.component.css']
})
export class ProductosListaComponent implements OnInit {
faEdit=faPen
  constructor(private productosServ: ProductosService) { }
  ngOnInit(): void {
    this.products=this.productosServ.getProductos()
    
  }

  products: any [] = []
}
