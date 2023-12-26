import { Injectable } from '@angular/core';
import { producto } from '../../assets/data/productos';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  constructor() { }
  public getProductos(){
    return producto;
  }

  public getProductoById(id:string){
    return producto.filter(p=>p.codigo_SKU==id)[0]
  }

  public addProducto(prov:any){
    producto.push(prov);
  }

  public editProducto(id:string, prod:any){
    
  }
  public deleteProducto(id:string){
    // producto = producto.filter(p=>p.codigoSKU!==id)
  }
}
interface Product {
  cod_proveedor: string,
  codigo_SKU: string,
  categoria: string,
  nombre_producto: string,
  descripcion: string,
  precio: number

}