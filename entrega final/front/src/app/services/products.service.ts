import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { environment } from 'src/environments/environment.development';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategoriesService } from './categories.service';
import { Supplier } from '../models/supplier';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {


  private API_URL = environment.API_URL + 'product';


  constructor(private http: HttpClient,
    private categoryService: CategoriesService
  ) {
  }

  public getProducts(): Observable<any> {
    return this.http.get(this.API_URL, { observe: "response" });
  }

  public getProductById(id: string): Observable<any> {
    return this.http.get(this.API_URL + '/' + id, { observe: "response" })
  }

  // getProductsbySupplier(idSup:string):Product[]{
  //   return this.productsData.filter(p=>p.cod_proveedor==idSup)  
  // }

  public addProduct(product: Product): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<any>(this.API_URL, product, { headers });
  }

  public editProduct(product: Product): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put<Product>(this.API_URL + '/' + product.prodId, product, { headers})
  }


  public deleteProduct(product:Product): Observable<any> {

    return this.http.delete(this.API_URL + '/' + product.prodId, { responseType: 'text' })
  }


  public undeleteProduct(id: number): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put(this.API_URL + '/undelete/' + id, { headers})
  }

  public getProductBySearch(filters: any): Observable<any> {
    let params = new HttpParams();
    const keys = Object.keys(filters)

    for (let key of keys) {
      if (filters[key] !== '' && filters[key] !== null)
        params = params.append(key, filters[key])
    }

    return this.http.get(this.API_URL + '/q?', { params: params, observe: "response" })
  }

  public getProductBySupplier(sup:Supplier):Observable<any>{
    return this.http.get(this.API_URL+'/by?supplier='+sup.supId, { observe: "response" })
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