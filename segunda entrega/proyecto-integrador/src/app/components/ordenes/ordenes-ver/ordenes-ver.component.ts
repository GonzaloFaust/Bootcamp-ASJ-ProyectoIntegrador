import { Component } from '@angular/core';
import { ProductosService } from 'src/app/services/productos.service';
import { ProveedoresService } from 'src/app/services/proveedores.service';
import { ActivatedRoute } from '@angular/router';
import { OrdenesService,blankOrder } from 'src/app/services/ordenes.service';
import { Order } from 'src/app/models/order';

@Component({
  selector: 'app-ordenes-ver',
  templateUrl: './ordenes-ver.component.html',
  styleUrls: ['./ordenes-ver.component.css']
})
export class OrdenesVerComponent {

  constructor(public ordenService: OrdenesService, public productoService: ProductosService, public provServ: ProveedoresService, private ruta: ActivatedRoute) { }
   order:Order=blankOrder;
   idOrden=this.ruta.snapshot.paramMap.get("id-orden")!

  ngOnInit(): void {
    this.order = this.ordenService.getOrdenById(this.idOrden)
    // this.razonSocial = this.provServ.getProveedorById(this.orden.cod_proveedor)?.razon_social
  }
  
  
}
