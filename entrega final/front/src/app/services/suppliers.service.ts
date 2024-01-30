import { Injectable } from '@angular/core';
import { supplier } from '../../assets/data/suppliers'
import { NgForm } from '@angular/forms';
import { Supplier } from '../models/supplier';
import { supplierCategory } from '../models/supplierCategory';

@Injectable({
  providedIn: 'root'
})
export class SuppliersService {
  suppliersData: Array<Supplier> = []

  PREFIX: string = "PROV";
  counter: number = 1;
  supplierTemplate: Supplier = structuredClone(blankProvider);

  constructor() {
    this.suppliersData = JSON.parse(localStorage.getItem('suppliers')!) || [...supplier]
  }

  public getSuppliers(): Supplier[] {
    return this.suppliersData;
  }

  public getSupplierById(id: string): Supplier {
    const prov = this.suppliersData.filter(p => p.codigo == id)[0]
    this.supplierTemplate = prov;
    return prov;
  }

  public addSupplier(): void {
    let newId = this.PREFIX + this.counter;
    while (this.suppliersData.some(p => p.codigo === newId)) {
      this.counter++;
      newId = this.PREFIX + this.counter;
    }
    this.suppliersData.push({ ...this.supplierTemplate, codigo: newId });
    this.supplierTemplate = structuredClone(blankProvider)
    this.saveData()
  }

  public editSupplier(id:string): void {
    this.supplierTemplate = { ...structuredClone(this.supplierTemplate), codigo:id }

    this.saveData()
    this.supplierTemplate = structuredClone(blankProvider)
  }

  public deleteSupplier(id: string): void {
    let prov= this.getSupplierById(id)
    prov.active=false;
    this.saveData()
  }


  private saveData() {
    localStorage.setItem('suppliers', JSON.stringify(this.suppliersData))
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