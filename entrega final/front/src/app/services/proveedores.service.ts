import { Injectable } from '@angular/core';
import { proveedor } from '../../assets/data/proveedores'
import { NgForm } from '@angular/forms';
import { Supplier } from '../models/supplier';
import { supplierCategory } from '../models/supplierCategory';

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {
  proveedoresData: Array<Supplier> = []

  PREFIX: string = "PROV";
  counter: number = 1;
  proveedorTemplate: Supplier = structuredClone(blankProvider);

  constructor() {
    this.proveedoresData = JSON.parse(localStorage.getItem('suppliers')!) || [...proveedor]
  }

  public getProveedores(): Supplier[] {
    return this.proveedoresData;
  }

  public getProveedorById(id: string): Supplier {
    const prov = this.proveedoresData.filter(p => p.codigo == id)[0]
    this.proveedorTemplate = prov;
    return prov;
  }

  public addProveedor(): void {
    let newId = this.PREFIX + this.counter;
    while (this.proveedoresData.some(p => p.codigo === newId)) {
      this.counter++;
      newId = this.PREFIX + this.counter;
    }
    this.proveedoresData.push({ ...this.proveedorTemplate, codigo: newId });
    this.proveedorTemplate = structuredClone(blankProvider)
    this.saveData()
  }

  public editProveedor(id:string): void {
    this.proveedorTemplate = { ...structuredClone(this.proveedorTemplate), codigo:id }

    this.saveData()
    this.proveedorTemplate = structuredClone(blankProvider)
  }

  public deleteProveedor(id: string): void {
    let prov= this.getProveedorById(id)
    prov.active=false;
    this.saveData()
  }


  private saveData() {
    localStorage.setItem('suppliers', JSON.stringify(this.proveedoresData))
  }
}


export const blankProvider: Supplier = {
  codigo: "",
  razon_social: "",
  rubro: "" as supplierCategory,
  sitio_web: "",
  email: "",
  telefono: "",
  direccion: {
    calle_numero: "",
    cp: "",
    pais: "",
    provincia: "",
    localidad: "",
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
  },
  active: true
}