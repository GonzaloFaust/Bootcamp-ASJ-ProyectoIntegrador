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
import Swal from 'sweetalert2';
import { supplier } from 'src/assets/data/suppliers';
import { environment } from 'src/environments/environment.development';


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
    "prodQuantity": '',
    "price":''
  }

  suppliers: Supplier[] = []
  orderStatus: OrderStatus[] = [];
  products: Product[] = [];

  idParam = this.route.snapshot.paramMap.get("id-order");
  isEditSession: boolean = this.idParam !== null;

  totalPrice:number=0;
  
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
                next: (data: HttpResponse<OrderDetail[]>) => {
                   this.orderDetail = data.body! ;
                   this.calculateTotalPrice();
                  }
              });
            this.getProductsBySupplier();
          }
        })


    }
  }

  createOrden() {
    if(this.isEditSession){
      this.orderService.editOrder(this.order).subscribe(
        {
          next: (data) => {

            Swal.fire({
              position: "center",
              icon: "success",
              title: `Orden  ${this.order.supplier.supBussinessName} modificada exitosamente`,
  
              showConfirmButton: false,
              timer: 1500
            })
            setTimeout(() => this.router.navigateByUrl('/products'), 1000)
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
    }
    else{
      this.orderService.addOrder(this.order).subscribe(
        {
          next: (data) => { console.log( data);
            for(let det of this.orderDetail){
              det.order=data;
              this.orderService.addOrderDetail(det).subscribe(
                {
                  next: (data) => {
        
                    Swal.fire({
                      position: "center",
                      icon: "success",
                      title: `Orden  ${this.order.supplier.supBussinessName} creada exitosamente`,
          
                      showConfirmButton: false,
                      timer: 1500
                    })
                    setTimeout(() => this.router.navigateByUrl('/products'), 1000)
                  },
                  error: (error) => {
                    console.log(error.error)
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
            }
          
          
          },
          error: (error) => console.log("falla en orden",error.error)
        }
      )
      
    }
  }

  cancelOrder() {
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

  getProductsBySupplier() {
    this.productService.getProductBySupplier(this.order.supplier).subscribe({
      next: (data: HttpResponse<Product[]>) => { this.products = data.body! }
    })
  }

  handleImage(sup:Supplier):string {
    let supplier:Supplier = this.suppliers.filter((s:Supplier)=>s.supId==this.order.supplier.supId)[0]

    return  supplier? supplier.supImage:"https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Blank_square.svg/2048px-Blank_square.svg.png"
  }
  handleImageError(image: HTMLImageElement) {
    image.src = environment.SUP_IMAGE_MOCK
  }

  calculateTotalPrice(){
    this.totalPrice=0;
    for(let det of this.orderDetail){
      this.totalPrice+=det.prodQuantity*det.price;
    }
  }

  addProduct(quantity: string) {
    let productSelected=this.products.filter(p=>p.prodId== this.orderDetailProduct.product.prodId)[0];
     this.orderDetail.push({
      "order": this.orderDetailProduct.order,
      "product":productSelected,
      "prodQuantity": parseInt(quantity),
      "price":productSelected.prodPrice
    })
    this.calculateTotalPrice();
    this.orderDetailProduct.product={
      "prodId": ''
    };
    this.orderDetailProduct.prodQuantity=''

  }

  deleteProduct(product:Product) {
    this.orderDetail= this.orderDetail.filter(o=>o.product.prodId!==product.prodId)
    this.calculateTotalPrice();
  }
}
