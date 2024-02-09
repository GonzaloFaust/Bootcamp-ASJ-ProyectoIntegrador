import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Country } from '../models/country';


@Injectable({
  providedIn: 'root'
})
export class LocalizationService {
  private COUNTRIES:string= 'country'
  private STATES:string= 'state'
  private API_URL=environment.API_URL
  
  constructor(private http: HttpClient){}

  getCountries():Observable<any>{
  return this.http.get(this.API_URL+this.COUNTRIES,{observe:"response"})

  }

  getStates(countryId:number):Observable<any>{
    return this.http.get(this.API_URL+this.STATES+'/by?country='+countryId, {observe:"response"})
  }


}
