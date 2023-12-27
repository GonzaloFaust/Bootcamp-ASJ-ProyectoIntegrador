import { Injectable } from '@angular/core';
import { producto } from '../../assets/data/productos';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  productosData: Array<Product> = []

  productTemplate: Product = structuredClone(blankProduct);

  constructor() { 
    this.productosData=[...producto]
  }
  public getProductos() {
    return this.productosData;
  }

  public getProductoById(id: string) {
    const prod= this.productosData.filter(p => p.id == id)[0]
    this.productTemplate = prod;
    return prod;
  }

  public addProducto(prov: any) {
    this.productosData.push({...this.productTemplate, id:this.productTemplate.cod_proveedor+this.productTemplate.codigo_SKU});
    this.productTemplate= structuredClone(blankProduct)
  }

  public editProducto(id: string) {
    const prod= this.getProductoById(id)
    this.productTemplate = {...structuredClone(this.productTemplate),id:id}
    this.productTemplate = structuredClone(blankProduct)
  }

  public deleteProducto(id: string) {
    this.productosData= this.productosData.filter(p=>p.id!==id)
  }
}


export const blankProduct: Product = {
  id: "",
  cod_proveedor: "",
  codigo_SKU: "",
  categoria: "",
  image: "",
  nombre_producto: "",
  descripcion: "",
  precio: 0

}