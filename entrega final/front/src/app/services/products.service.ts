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
  productsData: Array<Product> = []

  // product: Product = {
  //   "prodId": 0,
  //   "prodSku": "",
  //   "supplier": {
  //     "supId": 0,
  //     "supCode": "",
  //     "supBussinessName": "",
  //     "field": {
  //       "fieldId": 0,
  //       "fieldName": "",
  //       "fieldDetail": ""
  //     },
  //     "supImage": "",
  //     "supWebsite": "",
  //     "supEmail": "",
  //     "supTelephone": "",
  //     "address": {
  //       "addrId": 0,
  //       "state": {
  //         "stateId": 0,
  //         "country": {
  //           "counId": 0,
  //           "counName": ""
  //         },
  //         "stateName": ""
  //       },
  //       "cityName": "",
  //       "addrPostcode": "",
  //       "addrStreet": "",
  //       "addrNumber": 0,
  //       "addrFloor": 0,
  //       "addrApartment": ""
  //     },
  //     "supCuit": "",
  //     "taxCond": {
  //       "taxId": 0,
  //       "taxCondTitle": ""
  //     },
  //     "supContact": {
  //       "supContactId": 0,
  //       "supContactName": "",
  //       "supContactLastname": "",
  //       "supContactTelephone": "",
  //       "supContactEmail": "",
  //       "supContactRole": "",
  //     },
  //     "isActive": true,
  //   },
  //   "category": {
  //     "catId": 0,
  //     "catName": "",
  //     "catDetail": "",
  //     "field": {
  //       "fieldId": 0,
  //       "fieldName": "",
  //       "fieldDetail": ""
  //     },
  //   },
  //   "prodImage": "",
  //   "prodName": "",
  //   "prodDescription": "",
  //   "prodPrice": 0,
  //   "createdAt": "",
  //   "updatedAt": "",
  //   "prodAvailable": true
  // };

  constructor(private http:HttpClient,
    private categoryService:CategoriesService
    ) 
  {
    this.productsData = JSON.parse(localStorage.getItem('products')!) || [...product]
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
    console.log("esto vino: ", product)
    return this.http.post<Product>(this.API_URL,product,{headers });
    // this.productTemplate = structuredClone(blankProduct)
    this.saveData()
  }

  // public editProduct(id: string):void {
  //   this.productTemplate = { ...structuredClone(this.productTemplate), id:id }
  //   this.saveData()
  //   this.productTemplate = structuredClone(blankProduct)
  // }

  // public deleteProduct() :void{
  //   this.productsData = this.productsData.filter(p => p.id !== this.productTemplate.id)
  //   this.saveData()
  // }


  private saveData(){
    localStorage.setItem('products',JSON.stringify(this.productsData))
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