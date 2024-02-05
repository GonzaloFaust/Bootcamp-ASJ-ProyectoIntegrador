import { Injectable } from '@angular/core';
import { supplier } from '../../assets/data/suppliers'
import { NgForm } from '@angular/forms';
import { Supplier } from '../models/supplier';
import { Field } from '../models/fields';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SuppliersService {
  suppliersData: Array<Supplier> = []

  private API_URL="http://localhost:8080/supplier"
  

  PREFIX: string = "PROV";
  counter: number = 1;
  supplierTemplate: Supplier | undefined;

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

  // public addSupplier(): void {
  //   let newId = this.PREFIX + this.counter;
  //   while (this.suppliersData.some(p => p.codigo === newId)) {
  //     this.counter++;
  //     newId = this.PREFIX + this.counter;
  //   }
  //   this.suppliersData.push({ ...this.supplierTemplate, codigo: newId });
  //   this.supplierTemplate = structuredClone(blankProvider)
  //   this.saveData()
  // }

  // public editSupplier(id:string): void {
  //   this.supplierTemplate = { ...structuredClone(this.supplierTemplate), codigo:id }

  //   this.saveData()
  //   this.supplierTemplate = structuredClone(blankProvider)
  // }

  public deleteSupplier(id: string): void {
    let sup:Supplier;
    this.getSupplierById(id).subscribe(
      {
        next:(data:HttpResponse<Supplier>)=>{
          sup=data.body!
        sup.isActive=false;
        this.saveData()
        } ,
         error: (error:any)=> console.log(error)
      }
    )
    
  }


  private saveData() {
    localStorage.setItem('suppliers', JSON.stringify(this.suppliersData))
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