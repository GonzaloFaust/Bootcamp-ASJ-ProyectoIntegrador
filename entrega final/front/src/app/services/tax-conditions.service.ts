import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaxConditionsService {

  private API_URL="http://localhost:8080/tax-condition"
  constructor(private http:HttpClient) { }

  getTaxConditions() :Observable<any>{
    return this.http.get(this.API_URL,{observe: 'response'});
  }
}
