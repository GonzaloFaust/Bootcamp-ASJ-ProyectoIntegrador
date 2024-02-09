import { Component } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
import { SuppliersService } from 'src/app/services/suppliers.service';
import { ActivatedRoute } from '@angular/router';
import { OrdersService} from 'src/app/services/orders.service';
import { Order } from 'src/app/models/order';

@Component({
  selector: 'app-orders-view',
  templateUrl: './orders-view.component.html',
  styleUrls: ['./orders-view.component.css']
})
export class OrdersViewComponent {

  constructor(public orderService: OrdersService, public productoService: ProductsService, public provServ: SuppliersService, private ruta: ActivatedRoute) { }
 
    order:any={
      "ordId":'',
        "ordStatus":{
          "ordstId":''
        },
        "ordIssueDate":'',
        "ordExpDeliverDate":'',
        "ordDeliveryInfo":'',
        "supplier":{
          "supId":''
        }
    }
   idOrder=this.ruta.snapshot.paramMap.get("id-order")!

  ngOnInit(): void {
    this.order = this.orderService.getOrderById(this.idOrder)
    // this.razonSocial = this.provServ.getProveedorById(this.orden.cod_proveedor)?.razon_social
  }
  
  
}
