import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import '../../assets/data/geo/countries.json'


@Injectable({
  providedIn: 'root'
})
export class LocalizationService {
  private COUNTRIES:string= '../../assets/data/geo/countries.json'
  private STATES:string= '../../assets/data/geo/states.json'
  private CITIES:string= '../../assets/data/geo/cities.json'
  
  constructor(private http: HttpClient){}

  getCountries(){
  return this.http.get(this.COUNTRIES)

  }

  getStates(){
    return this.http.get(this.STATES)
  }

  getCities(){
    return this.http.get(this.CITIES)
  }
}
