import { Injectable } from '@angular/core';
import { producto } from '../data/productos';

@Injectable({
  providedIn: 'root'
})
export class ServiceProductosService {

  constructor() { }
  public getProductos(){
    return producto;
  }

  public getProductoById(id:string){
    return producto.filter(p=>p.codigoSKU==id)[0]
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
