import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import { Observable } from 'rxjs';
import { Supplier } from '../models/supplier';
@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  private API_URL="http://localhost:8080/category"
  constructor(private http:HttpClient) { }

  getCategories() :Observable<any>{
    return this.http.get(this.API_URL,{observe: 'response'});
  }

  getCategoryById(id:number) :Observable<any>{
    return this.http.get(this.API_URL+'/'+id ,{observe: 'response'});
  }

  getCategoriesBySupplier(supplier:number):Observable<any>{
    return this.http.get(this.API_URL+'/by?supplier='+supplier ,{observe: 'response'})
  }
}
