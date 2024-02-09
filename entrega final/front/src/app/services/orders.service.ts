import { Injectable } from '@angular/core';
import { purchaseOrder } from '../../assets/data/orders';
import { Order } from '../models/order';
import { OrderStatus } from '../models/orderStatus';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { OrderDetail } from '../models/orderDetail';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {


  private API_URL = environment.API_URL + 'purchase-order';
  private API_URL_ORDER_STATUS = environment.API_URL + 'order-status'
  private API_URL_ORDER_DETAIL = this.API_URL + '-product'
  constructor(private http: HttpClient
  ) {
  }

  public getOrders(): Observable<any> {
    return this.http.get(this.API_URL, { observe: "response" });
  }

  public getByStatus(status: string): Observable<any> {
    return this.http.get(this.API_URL + '/by?status=' + status, { observe: "response" })
  }

  public getOrderById(id: string): Observable<any> {
    return this.http.get(this.API_URL + '/' + id, { observe: "response" });
  }

  public addOrder(order: Order): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post(this.API_URL, order, { headers })
  }

  public editOrder(order: Order): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put(this.API_URL + "/" + order.ordId, order, { headers , responseType: 'text' as 'json' })
  }
  public addOrderDetail(detail: OrderDetail): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post(this.API_URL_ORDER_DETAIL, detail, { headers })
  }

  public deleteOrder(order: Order): Observable<any> {
    return this.http.delete(this.API_URL + '/' + order.ordId, { responseType: 'text' })
  }


  public getAllOrderStatus(): Observable<any> {
    return this.http.get(this.API_URL_ORDER_STATUS, { observe: "response" });
  }

  public getOrderDetail(order: Order): Observable<any> {
    return this.http.get(this.API_URL + '-product/by?order=' + order.ordId, { observe: "response" });
  }

}






