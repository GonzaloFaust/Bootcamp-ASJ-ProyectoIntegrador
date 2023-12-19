import { Injectable } from '@angular/core';
import { ordenCompra } from '../data/ordenes';

@Injectable({
  providedIn: 'root'
})
export class ServiceOrdenesService {

  constructor() { }

  public getOrdenesCompra(){
    return ordenCompra;
  }

  public getOrdenesById(id:string){
    return ordenCompra.filter(p=>p.numeroOrdenCompra==id)[0]
  }

  public addOrden(orden:any){
    ordenCompra.push(orden);
  }

  public editOrdenCompra(id:string, orden:any){
    
  }
  public deleteOrden(id:string){
    // ordenCompra = ordenCompra.filter(p=>p.numeroOrdenCompra!==id)
  }
}
