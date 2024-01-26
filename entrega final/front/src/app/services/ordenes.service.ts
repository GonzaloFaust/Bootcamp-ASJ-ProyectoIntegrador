import { Injectable } from '@angular/core';
import { ordenCompra } from '../../assets/data/ordenes';
import { Order } from '../models/order';
import { OrderState } from '../models/orderState';

@Injectable({
  providedIn: 'root'
})
export class OrdenesService {

  PREFIX: string = "OC";
  counter: number = 1;

  ordersData: Array<Order> = []

  orderTemplate: Order = structuredClone(blankOrder);

  constructor() {
    this.ordersData = JSON.parse(localStorage.getItem('orders')!) || [...ordenCompra]
  }

  public getOrdenesCompra(): Order[] {
    return this.ordersData;
  }

  public getOrdenById(id: string): Order {
    const ord= this.ordersData.filter(o => o.numero_orden_compra == id)[0]
    this.orderTemplate = ord;
    console.log(this.orderTemplate)
    return ord;
  }

  public addOrden(): void {
    let newId = this.PREFIX + this.counter;
    while (this.ordersData.some(p => p.numero_orden_compra === newId)) {
      this.counter++;
      newId = this.PREFIX + this.counter;
    }
    this.ordersData.push({ ...this.orderTemplate, numero_orden_compra: this.PREFIX+this.counter++ });
    this.orderTemplate = structuredClone(blankOrder)
    this.saveData()
  }

  public editOrdenCompra(id:string): void {
  this.orderTemplate = { ...structuredClone(this.orderTemplate), numero_orden_compra:id }
  this.saveData()
  this.orderTemplate = structuredClone(blankOrder)
}

  public deleteOrden(id: string): void {
    let ord= this.getOrdenById(id);
    ord.state= OrderState.cancelado
    this.saveData()
  }


  private saveData() {
    localStorage.setItem('orders', JSON.stringify(this.ordersData))
  }
}

export const blankOrder: Order = {
  numero_orden_compra: "",
  state: "pendiente" as OrderState,
  fecha_emision: "",
  fecha_entrega_esperada: "",
  informacion_recepcion: {
    direccion: {
      calle_numero: "",
      cp: "",
      localidad: "",
      provincia: "",
      pais: ""
    }
  },
  cod_proveedor: "",
  productos: [
    {
      codigo_SKU: "",
      cantidad: 0
    }
  ]
}


//   public deleteProducto() {
//   this.productosData = this.productosData.filter(p => p.id !== this.productTemplate.id)
//   this.saveData()
// }



