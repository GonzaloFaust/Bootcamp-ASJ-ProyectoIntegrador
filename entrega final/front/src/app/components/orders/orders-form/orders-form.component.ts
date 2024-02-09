import { Component, OnInit } from '@angular/core';
import { NgForm, FormControl } from '@angular/forms';
import { OrdersService } from 'src/app/services/orders.service';
import { SuppliersService } from 'src/app/services/suppliers.service';
import { ProductsService } from 'src/app/services/products.service';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderStatus } from 'src/app/models/orderStatus';
import { Order } from 'src/app/models/order';
import { Supplier } from 'src/app/models/supplier';
import { Product } from 'src/app/models/product';
import { OrderDetail } from 'src/app/models/orderDetail';
import { HttpResponse } from '@angular/common/http';
import { faTrash } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-orders-form',
  templateUrl: './orders-form.component.html',
  styleUrls: ['./orders-form.component.css'],
})
export class OrdersFormComponent implements OnInit {

  faDelete=faTrash
  constructor(private route: ActivatedRoute, public orderService: OrdersService, private supplierService: SuppliersService, private productService: ProductsService, private router: Router) { }

  order: any = {
    "ordStatus": {
      "ordstId": 1,
    },
    "ordIssueDate": "",
    "ordExpDeliverDate": "",
    "ordDeliveryInfo": "",
    "supplier": {
      "supId": ''
    }

  }

  orderDetail: OrderDetail[] = []
  orderDetailProduct: any = {
    "order": {
      "ordId": '',
    },
    "product": {
      "prodId": ''
    },
    "prodQuantity": ''
  }

  suppliers: Supplier[] = []
  orderStatus: OrderStatus[] = [];
  products: Product[] = [];

  idParam = this.route.snapshot.paramMap.get("id-order");
  isEditSession: boolean = this.idParam !== null;

  // proveedores = this.provService.getSuppliers().map(prov => { return { cod_proveedor: prov.codigo, razon_social: prov.razon_social } })

  minFechaEmision: string = new Date().toISOString().split('T')[0];
  maxFechaActual: string = new Date().toISOString();


  ngOnInit(): void {

    this.supplierService.getSuppliers().subscribe(
      {
        next: (data: HttpResponse<Supplier[]>) => { this.suppliers = data.body! }
      }
    )

    this.orderService.getAllOrderStatus().subscribe(
      {
        next: (data: HttpResponse<OrderStatus[]>) => { this.orderStatus = data.body! }
      })
    if (this.isEditSession) {

      this.orderService.getOrderById(this.idParam!).subscribe(
        {
          next: (data: HttpResponse<Order>) => {
            this.order = data.body!; 
            // this.order.ordIssueDate= this.formatDate(data.body.ordIssueDate)
            this.orderService.getOrderDetail(this.order).subscribe(
              {
                next: (data: HttpResponse<OrderDetail[]>) => { this.orderDetail = data.body! }
              });
            this.getProductosBySupplier();
          }
        })


    }
  }

  createOrden() {
    if(this.isEditSession){
      this.orderService.editOrder(this.order).subscribe(
        {
          next: (data) => { console.log( data.body!) },
          error: (error) => console.log(error.error)
        }
      )
    }
    else{
      this.orderService.addOrder(this.order).subscribe(
        {
          next: (data) => { console.log( data);
            for(let det of this.orderDetail){
              det.order=data;
              this.orderService.addOrderDetail(det).subscribe(
                {
                  next: (data: HttpResponse<Product>) => {console.log( data.body!); },
                  error: (error: any) => console.log("falla en detalle ",error.error)
                }
              )
            }
          
          
          },
          error: (error) => console.log("falla en orden",error.error)
        }
      )
      
    }
  }

  cancelarOrden() {
    this.orderService.deleteOrder(this.order).subscribe({
      next: (data: HttpResponse<Product[]>) => { this.products = data.body! }
    })
  }

  formatDate(date: Date): string { // me la dio el chat
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
}

  getProductosBySupplier() {
    this.productService.getProductBySupplier(this.order.supplier).subscribe({
      next: (data: HttpResponse<Product[]>) => { this.products = data.body! }
    })
  }

  agregarProducto(prod: number, quantity: string) {
    console.log(this.orderDetailProduct.product)
    this, this.orderDetail.push({
      "order": this.orderDetailProduct.order,
      "product": this.orderDetailProduct.product,
      "prodQuantity": parseInt(quantity)
    })
    this.orderDetailProduct.product={
      "prodId": ''
    };
    this.orderDetailProduct.prodQuantity=''

  }

  deleteProduct(product:Product) {
    this.orderDetail= this.orderDetail.filter(o=>o.product.prodId!==product.prodId)
  }
}
