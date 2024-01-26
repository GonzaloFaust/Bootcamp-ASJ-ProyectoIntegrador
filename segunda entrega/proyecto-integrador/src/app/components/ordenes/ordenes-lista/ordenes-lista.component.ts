import { Component } from '@angular/core';
import { OrdenesService } from 'src/app/services/ordenes.service';
import { ProveedoresService } from 'src/app/services/proveedores.service';
import { ProductosService } from 'src/app/services/productos.service';

@Component({
  selector: 'app-ordenes-lista',
  templateUrl: './ordenes-lista.component.html',
  styleUrls: ['./ordenes-lista.component.css']
})
export class OrdenesListaComponent {

  ordenes: any[] = []
  constructor(public service: OrdenesService, private servProv: ProveedoresService, private prodServ: ProductosService) {

  }
  ngOnInit(): void {
    this.updateLista()
  }

  deleteOrder(id: string) {
    this.service.deleteOrden(id)
    this.updateLista();
  }

  private updateLista() {
    this.ordenes = this.service.getOrdenesCompra();
    this.ordenes = this.ordenes.map(o => { return { ...o, nombre_proveedor: this.servProv.getProveedorById(o.cod_proveedor).razon_social } })
    
  }
}
