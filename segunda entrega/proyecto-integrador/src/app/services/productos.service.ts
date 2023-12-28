import { Injectable } from '@angular/core';
import { producto } from '../../assets/data/productos';
import { Product } from '../models/product';
import { productCategory } from '../models/productCategory';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  productosData: Array<Product> = []

  productTemplate: Product = structuredClone(blankProduct);

  constructor() {
    this.productosData = JSON.parse(localStorage.getItem('products')!) || [...producto]
  }

  public getProductos():Product[]
  {
    return this.productosData;
  }

  public getProductoById(id: string) :Product{
    const prod = this.productosData.filter(p => p.id == id)[0]
    this.productTemplate = prod;
    return prod;
  }

  getProductosbyProveedor(idProv:string):Product[]{
    return this.productosData.filter(p=>p.cod_proveedor==idProv)  
  }

  public addProducto(prov: any):void {
    this.productosData.push({ ...this.productTemplate, id: this.productTemplate.cod_proveedor + this.productTemplate.codigo_SKU });
    this.productTemplate = structuredClone(blankProduct)
    this.saveData()
  }

  public editProducto(id: string):void {
    this.productTemplate = { ...structuredClone(this.productTemplate), id:id }
    this.saveData()
    this.productTemplate = structuredClone(blankProduct)
  }

  public deleteProducto() :void{
    this.productosData = this.productosData.filter(p => p.id !== this.productTemplate.id)
    this.saveData()
  }


  private saveData(){
    localStorage.setItem('products',JSON.stringify(this.productosData))
  }
}




export const blankProduct: Product = {
  id: "",
  cod_proveedor: "",
  codigo_SKU: "",
  categoria: "" as productCategory,
  image: '../../assets/image/imagen-producto-mock.jpg',
  nombre_producto: "",
  descripcion: "",
  precio: 0

}