import { Component, OnInit } from '@angular/core';
import { ServiceProveedoresService } from 'src/app/services/service-proveedores.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-proveedores-crear',
  templateUrl: './proveedores-crear.component.html',
  styleUrls: ['./proveedores-crear.component.css']
})
export class ProveedoresCrearComponent {

  idParam = this.route.snapshot.paramMap.get("idProveedor");
  isEditSession: boolean = this.idParam === null;

  proveedorTemplate = this.service.getProveedorById(this.idParam!)|| {
    razonSocial: "",
    rubro: "Mayorista",
    sitioWeb: "",
    email: "",
    telefono: "",
    direccion: {
      calleNumero: "",
      cp: "",
      localidad: "",
      provincia: "",
      pais: "Cuba"
    },
    datosFiscales: {
      cuit: "",
      condicionIva: ""
    },
    datosContacto: {
      nombre: "",
      apellido: "",
      telefonoContacto: "",
      emailContacto: "",
      rol: ""
    }
  }
  

  constructor(private service: ServiceProveedoresService, private route: ActivatedRoute) {}


  editarProveedor() { 
    this.service.editProveedor(this.idParam!,this.proveedorTemplate)
  }

  agregarProveedor() {

    this.service.addProveedor(this.proveedorTemplate)
  }
}
