import { Injectable } from '@angular/core';
import { ordenCompra } from '../../assets/data/ordenes';

@Injectable({
  providedIn: 'root'
})
export class OrdenesService {



  constructor() { }

  public getOrdenesCompra() {
    return ordenCompra;
  }

  public getOrdenesById(id: string) {
    return ordenCompra.filter(p => p.numero_orden_compra == id)[0]
  }

  public addOrden(orden: any) {
    ordenCompra.push(orden);
  }

  public editOrdenCompra(id: string, orden: any) {

  }
  public deleteOrden(id: string) {
    // ordenCompra = ordenCompra.filter(p=>p.numeroOrdenCompra!==id)
  }
}

interface Order {
  numero_orden_compra:string,
  fecha_emision: string,
  fecha_entrega_esperada: string,
  informacion_recepcion: {
      direccion: {
          calle_numero: string,
          cp: string,
          localidad: string,
          provincia: string,
          pais: string
      }
  },
  cod_proveedor: string,
  productos: [
      {
          codigo_SKU: string,
          cantidad: number
      } 
  ]
}