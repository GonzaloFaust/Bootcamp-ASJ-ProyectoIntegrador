import { Component } from '@angular/core';
import { OrdersService } from 'src/app/services/orders.service';
import { SuppliersService } from 'src/app/services/suppliers.service';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent {

  ordenes: any[] = []
  constructor(public service: OrdersService, private servProv: SuppliersService, private prodServ: ProductsService) {

  }
  ngOnInit(): void {
    this.updateLista()
  }

  deleteOrder(id: string) {
    this.service.deleteOrder(id)
    this.updateLista();
  }

  private updateLista() {
    this.ordenes = this.service.getOrders();
    // this.ordenes = this.ordenes.map(o => { return { ...o, nombre_proveedor: this.servProv.getSupplierById(o.cod_proveedor).razon_social } })
    
  }
}
