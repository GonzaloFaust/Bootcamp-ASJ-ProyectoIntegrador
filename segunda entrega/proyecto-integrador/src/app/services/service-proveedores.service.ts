import { Injectable } from '@angular/core';
import {proveedor} from '../data/proveedores'

@Injectable({
  providedIn: 'root'
})
export class ServiceProveedoresService {
proveedoresData:any[]=[]

  constructor() {
    this.proveedoresData= structuredClone(proveedor)
   }

  public getProveedores(){
    return this.proveedoresData;
  }

  public getProveedorById(id:string){
    return this.proveedoresData.filter(p=>p.codigo==id)[0]
  }

  public addProveedor(prov:any){
    this.proveedoresData.push({codigo:this.getProveedores().length +1,...prov});
  }

  public editProveedor(id:string, prov:any){

  }

  public deleteProveedor(id:string){
    this.proveedoresData = this.proveedoresData.filter(p=>p.codigo!==id)
  }
  
}
