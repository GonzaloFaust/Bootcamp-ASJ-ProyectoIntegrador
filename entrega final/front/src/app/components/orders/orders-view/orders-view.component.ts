import { Component } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
import { SuppliersService } from 'src/app/services/suppliers.service';
import { ActivatedRoute } from '@angular/router';
import { OrdersService} from 'src/app/services/orders.service';
import { Order } from 'src/app/models/order';
import Swal from 'sweetalert2';
import { OrderDetail } from 'src/app/models/orderDetail';

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
          "ordstId":'',
          "ordstName":''
        },
        "ordIssueDate":'',
        "ordExpDeliverDate":'',
        "ordDeliveryInfo":'',
        "supplier":{
          "supId":''
        }
    }
    totalPrice=0;
    orderDetail:OrderDetail[]=[]
   idOrder=this.ruta.snapshot.paramMap.get("id-order")!

  ngOnInit(): void {
    this.order = this.orderService.getOrderById(this.idOrder).subscribe(
      {
        next: (data) => { this.order=data.body
          this.orderService.getOrderDetail(this.order).subscribe(
            {
              next: (data) => {
                 this.orderDetail = data.body! ;
                 this.calculateTotalPrice();
                }
            });
        },
        error: (error) => {
          Swal.fire({
            position: "center",
            icon: "error",
            title: error.error,

            showConfirmButton: false,
            timer: 1500
          })
        }
      }
    )
    // this.razonSocial = this.provServ.getProveedorById(this.orden.cod_proveedor)?.razon_social
  }
  
  calculateTotalPrice(){
    this.totalPrice=0;
    for(let det of this.orderDetail){
      this.totalPrice+=det.prodQuantity*det.price;
    }
  }
}
