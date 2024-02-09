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

  constructor(public ordenService: OrdersService, public productoService: ProductsService, public provServ: SuppliersService, private ruta: ActivatedRoute) { }
  //  order:Order=blankOrder;
   idOrden=this.ruta.snapshot.paramMap.get("id-orden")!

  ngOnInit(): void {
    // this.order = this.ordenService.getOrderById(this.idOrden)
    // this.razonSocial = this.provServ.getProveedorById(this.orden.cod_proveedor)?.razon_social
  }
  
  
}
