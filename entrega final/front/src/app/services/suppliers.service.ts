import { Injectable } from '@angular/core';
import { supplier } from '../../assets/data/suppliers'
import { NgForm } from '@angular/forms';
import { Supplier } from '../models/supplier';
import { Field } from '../models/fields';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class SuppliersService {
  suppliersData: Array<Supplier> = []

  private API_URL = environment.API_URL + 'supplier';

  constructor(private http:HttpClient) {
   // this.suppliersData = JSON.parse(localStorage.getItem('suppliers')!) || [...supplier]
  }

  public getSuppliers(): Observable<any> {
    return this.http.get(this.API_URL,{observe:"response"});
  }

  public getSupplierById(id: string): Observable<any> {
    return this.http.get(this.API_URL+'/'+id,{observe:"response"})
    //this.suppliersData.filter(p => p.codigo == id)[0]
    // this.supplierTemplate = prov;
    // return prov;
  }

  public addSupplier(sup:Supplier): Observable<any> {
    
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<Supplier>(this.API_URL, sup, { headers });

  }

  public editSupplier(sup:Supplier): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put<Supplier>(this.API_URL + '/' + sup.supId, sup, { headers})
  }

  public deleteSupplier(sup:Supplier): Observable<any> {
    return this.http.delete(this.API_URL + '/' + sup.supId, { responseType: 'text' })
    
  }

  public undeleteProduct(sup:Supplier): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put(this.API_URL + '/undelete/' + sup.supId, { headers})
  }

  public getSupplierBySearch(filters: any): Observable<any> {
    console.log(filters)
    let params = new HttpParams();
    const keys = Object.keys(filters)

    for (let key of keys) {
      if (filters[key] !== '' && filters[key] !== null)
        params = params.append(key, filters[key])
    }

    return this.http.get(this.API_URL + '/q?', { params: params, observe: "response" })
  }

}


// export const blankProvider: Supplier = {
//   codigo: "",
//   razon_social: "",
//   rubro: "" as supplierCategory,
//   sitio_web: "",
//   email: "",
//   telefono: "",
//   direccion: {
//     calle_numero: "",
//     cp: "",
//     pais: "",
//     provincia: "",
//     localidad: "",
//   },
//   datos_fiscales: {
//     cuit: "",
//     condicion_iva: ""
//   },
//   datos_contacto: {
//     nombre: "",
//     apellido: "",
//     telefono_contacto: "",
//     email_contacto: "",
//     rol: ""
//   },
//   active: true
// }