import { Injectable } from '@angular/core';
import { proveedor } from '../../assets/data/proveedores'
import { NgForm } from '@angular/forms';
import { Supplier } from '../models/supplier';
import { Rubro } from '../models/rubro';

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {
  proveedoresData: Array<Supplier> = []

  proveedorTemplate: Supplier = {...blankProvider}
  
  constructor() {
    this.proveedoresData= [...proveedor]
   }
   
  public getProveedores():Supplier[] {
    return this.proveedoresData;
  }

  public getProveedorById(id: string) {
    const prov= this.proveedoresData.filter(p => p.codigo == id)[0] 
    this.proveedorTemplate =prov;
    return  prov;
  }

  public addProveedor(form: NgForm) {
    this.proveedoresData.push({ ...this.proveedorTemplate, codigo: (this.getProveedores().length + 1).toString()});
    this.proveedorTemplate = {...blankProvider}
  }

  public editProveedor(id: string) {

  }

  public deleteProveedor(id: string) {
    this.proveedoresData = this.proveedoresData.filter(p => p.codigo !== id)
  }

}


export const blankProvider = {
  codigo: "",
  razon_social: "",
  rubro: "" as Rubro,
  sitio_web: "",
  email: "",
  telefono: "",
  direccion: {
    calle_numero: "",
    cp: "",
    localidad: "",
    provincia: "",
    pais: ""
  },
  datos_fiscales: {
    cuit: "",
    condicion_iva: ""
  },
  datos_contacto: {
    nombre: "",
    apellido: "",
    telefono_contacto: "",
    email_contacto: "",
    rol: ""
  }
}