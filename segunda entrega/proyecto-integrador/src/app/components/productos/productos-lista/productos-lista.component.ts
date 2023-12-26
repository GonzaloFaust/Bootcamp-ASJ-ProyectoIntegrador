import { Component, OnInit } from '@angular/core';
import { ProductosService } from 'src/app/services/productos.service';

@Component({
  selector: 'app-productos-lista',
  templateUrl: './productos-lista.component.html',
  styleUrls: ['./productos-lista.component.css']
})
export class ProductosListaComponent implements OnInit {

  constructor(private productosServ: ProductosService) { }
  ngOnInit(): void {
    this.products=this.productosServ.getProductos()
    
  }

  products: any [] = []
}
