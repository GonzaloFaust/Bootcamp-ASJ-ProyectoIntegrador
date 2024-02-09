import { Component } from '@angular/core';
import { OrdersService } from 'src/app/services/orders.service';
import { SuppliersService } from 'src/app/services/suppliers.service';
import { ProductsService } from 'src/app/services/products.service';
import { Order } from 'src/app/models/order';
import { OrderStatus } from 'src/app/models/orderStatus';
import { HttpResponse } from '@angular/common/http';
import { faPen, faEye, faTrash, faRefresh } from '@fortawesome/free-solid-svg-icons'
import Swal from 'sweetalert2';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent {
  faEdit = faPen
  faView = faEye
  faDelete = faTrash

  orders: any[] = []
  //   orders:Order={
  //     "ordId":0,
  //     "ordStatus":{OrderStatus},
  //     "ordIssueDate":'',
  //     "ordExpDeliverDate":'',
  //     "address":Address,
  //     "supplier":Supplier        
  // }
  orderStatus: OrderStatus[] = [];

  selectedOrderStatus: any = '';

  constructor(public orderService: OrdersService, private servProv: SuppliersService, private prodServ: ProductsService) {

  }
  ngOnInit(): void {
    this.updateLista()
    this.getAllOrdersStatus();
  }

  getAllOrdersStatus() {
    this.orderService.getAllOrderStatus().subscribe(
      {
        next: (data: HttpResponse<OrderStatus[]>) => { this.orderStatus = data.body! },
        error: (error) => {
          Swal.fire({
            position: "center",
            icon: "error",
            title: "Error al obtener informacion",
            showConfirmButton: false,
            timer: 1500
          });
        }
      }
    )
  }

  cancelOrder(order: Order) {
    Swal.fire({
      title: 'Cancelar orden',
      text: `Seguro desea cancelar la orden #${order.ordId}?`,
      icon: 'question',
      showCancelButton: true,
      allowOutsideClick: false,
      allowEscapeKey: false,
      confirmButtonColor: "#b11",
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar',
    })
      .then(res => {
        if (res.isConfirmed) {
          this.orderService.deleteOrder(order).subscribe(
            {
              next: (data) => {

                  Swal.fire({
                    position: "center",
                    icon: "success",
                    title: data.body,
                    showConfirmButton: false,
                    timer: 1500
                  });
                 
                },
                error: (error) => {
                  Swal.fire({
                    position: "center",
                    icon: "error",
                    title: error.error,
                    showConfirmButton: false,
                    timer: 1500
                  });
                }
            }
          );
          this.updateLista();
      }
    })
  }

  private updateLista() {
        this.orderService.getOrders().subscribe(
          {
            next: (data: HttpResponse<Order[]>) => { this.orders = data.body! },
            error: (error) => {
              Swal.fire({
                position: "center",
                icon: "error",
                title: "Error al obtener informacion",
                showConfirmButton: false,
                timer: 1500
              });
            }
          }
        )
      }




  search(){
    console.log(this.selectedOrderStatus)
        if(this.selectedOrderStatus == 'all'){
        this.updateLista()
      }
      else {
      this.orderService.getByStatus(this.selectedOrderStatus).subscribe(
        {
          next: (data: HttpResponse<Order[]>) => { this.orders = data.body! },
          error: (error) => {
            Swal.fire({
              position: "center",
              icon: "error",
              title: "Error al obtener informacion",
              showConfirmButton: false,
              timer: 1500
            });
          }
        })
    }
   
  }

  showTable():boolean{
    let count=0
    for(let ord of this.orders){
      if(ord.ordStatus.ordstId== this.selectedOrderStatus)
      count++;
    }
    return count>0;
  }
}
