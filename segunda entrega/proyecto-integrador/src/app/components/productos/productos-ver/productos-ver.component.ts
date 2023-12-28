import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';
import { Supplier } from 'src/app/models/supplier';
import { ProductosService, blankProduct } from 'src/app/services/productos.service';
import { ProveedoresService, blankProvider } from 'src/app/services/proveedores.service';
@Component({
  selector: 'app-productos-ver',
  templateUrl: './productos-ver.component.html',
  styleUrls: ['./productos-ver.component.css']
})
export class ProductosVerComponent implements OnInit{

  constructor(public productoService: ProductosService, public provServ: ProveedoresService, private ruta: ActivatedRoute) { }
   producto:Product=blankProduct;
   razonSocial:string="";

  ngOnInit(): void {
    this.producto = this.productoService.getProductoById(this.ruta.snapshot.paramMap.get("id-producto")!)
    this.razonSocial = this.provServ.getProveedorById(this.producto.cod_proveedor)?.razon_social
  }
  
  deleteProducto() {
    this.productoService.deleteProducto()
  }
}





