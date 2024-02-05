import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FieldsService {

  private API_URL="http://localhost:8080/field"
  constructor(private http:HttpClient) { }

  getFields() :Observable<any>{
    return this.http.get(this.API_URL,{observe: 'response'});
  }

  getFieldById(id:number) :Observable<any>{
    return this.http.get(this.API_URL+'/'+id ,{observe: 'response'});
  }
}
