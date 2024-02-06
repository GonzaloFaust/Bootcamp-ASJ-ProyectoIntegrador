import { Injectable } from '@angular/core';
import {product } from '../../assets/data/products';
import { Product } from '../models/product';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { CategoriesService } from './categories.service';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {


  private API_URL="http://localhost:8080/product"


  constructor(private http:HttpClient,
    private categoryService:CategoriesService
    ) 
  {
  }

  public getProducts():Observable<any>
  {
    return this.http.get(this.API_URL,{observe:"response"});
  }

  public getProductById(id: string) :Observable<any>{
    return this.http.get(this.API_URL+'/'+id,{observe:"response"})
  }

  // getProductsbySupplier(idSup:string):Product[]{
  //   return this.productsData.filter(p=>p.cod_proveedor==idSup)  
  // }

  public addProduct(product:Product):Observable<any> {
       const headers = { 'Content-Type': 'application/json' };
    return this.http.post<Product>(this.API_URL,product,{headers });
    // this.productTemplate = structuredClone(blankProduct)
    //this.saveData()
  }

  // public editProduct(id: string):void {
  //   this.productTemplate = { ...structuredClone(this.productTemplate), id:id }
  //   this.saveData()
  //   this.productTemplate = structuredClone(blankProduct)
  // }

  public deleteProduct(id:number) :Observable<any>{
    return this.http.delete<Product>(this.API_URL+'/'+id)
  }


  
}




// export const blankProduct: Product = {
//   id: "",
//   cod_proveedor: "",
//   codigo_SKU: "",
//   categoria: "" as productCategory,
//   image: '../../assets/image/imagen-producto-mock.jpg',
//   nombre_producto: "",
//   descripcion: "",
//   precio: 0

// }