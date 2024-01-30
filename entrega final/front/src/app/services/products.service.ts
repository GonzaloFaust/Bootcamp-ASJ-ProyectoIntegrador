import { Injectable } from '@angular/core';
import {product } from '../../assets/data/products';
import { Product } from '../models/product';
import { productCategory } from '../models/productCategory';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  productsData: Array<Product> = []

  productTemplate: Product = structuredClone(blankProduct);

  constructor() {
    this.productsData = JSON.parse(localStorage.getItem('products')!) || [...product]
  }

  public getProducts():Product[]
  {
    return this.productsData;
  }

  public getProductById(id: string) :Product{
    const prod = this.productsData.filter(p => p.id == id)[0]
    this.productTemplate = prod;
    return prod;
  }

  getProductsbySupplier(idSup:string):Product[]{
    return this.productsData.filter(p=>p.cod_proveedor==idSup)  
  }

  public addProduct(prov: any):void {
    this.productsData.push({ ...this.productTemplate, id: this.productTemplate.cod_proveedor + this.productTemplate.codigo_SKU });
    this.productTemplate = structuredClone(blankProduct)
    this.saveData()
  }

  public editProduct(id: string):void {
    this.productTemplate = { ...structuredClone(this.productTemplate), id:id }
    this.saveData()
    this.productTemplate = structuredClone(blankProduct)
  }

  public deleteProduct() :void{
    this.productsData = this.productsData.filter(p => p.id !== this.productTemplate.id)
    this.saveData()
  }


  private saveData(){
    localStorage.setItem('products',JSON.stringify(this.productsData))
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