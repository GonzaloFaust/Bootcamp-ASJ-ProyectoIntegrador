import { Component } from '@angular/core';
import { ProveedoresService } from 'src/app/services/proveedores.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-proveedores-ver',
  templateUrl: './proveedores-ver.component.html',
  styleUrls: ['./proveedores-ver.component.css']
})
export class ProveedoresVerComponent {
constructor(private proveedorService: ProveedoresService, private ruta: ActivatedRoute){}

  proveedor= this.proveedorService.getProveedorById(this.ruta.snapshot.paramMap.get("id-proveedor")!)

}
