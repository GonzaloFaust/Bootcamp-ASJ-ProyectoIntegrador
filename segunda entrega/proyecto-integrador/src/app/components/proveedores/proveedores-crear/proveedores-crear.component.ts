import { Component, OnInit } from '@angular/core';
import { ProveedoresService, blankProvider } from 'src/app/services/proveedores.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Rubro} from 'src/app/models/rubro';
import { CondicionIva } from 'src/app/models/condicionIva';
import { LocalizationService } from 'src/app/services/localization.service';

@Component({
  selector: 'app-proveedores-crear',
  templateUrl: './proveedores-crear.component.html',
  styleUrls: ['./proveedores-crear.component.css']
})
export class ProveedoresCrearComponent implements OnInit {

  idParam = this.route.snapshot.paramMap.get("id-proveedor");

  isEditSession: boolean = this.idParam !== null;

  rubrosPermitidos= Object.values(Rubro)
  condicionesIVA= Object.values(CondicionIva)

  constructor(public service: ProveedoresService, private route: ActivatedRoute, public geo:LocalizationService) { }

  countries:any[]=[]
  states:any[]=[]
  cities:any[]=[]

  ngOnInit(): void {
    if (this.isEditSession) { this.service.getProveedorById(this.idParam!.toString()) }
    else this.service.proveedorTemplate = {...blankProvider}
    this.getCountries()
  }
  updateProveedor() {
    this.service.editProveedor(this.idParam!)
  }

  createProveedor(form: NgForm) {

    this.service.addProveedor(form)
    
  }
  
  // ojo aca con el editar
  getCountries(){
    this.states=[];
    
    this.geo.getCountries().subscribe((data:any)=>this.countries=data)
  }
  
  getStates(){
    this.cities=[];
    this.geo.getStates().subscribe((data:any)=>this.states=data.filter((s:any)=>s.country_name===this.service.proveedorTemplate.direccion.pais))
  }

  getCities(){
    this.geo.getCities().subscribe((data:any)=>this.cities=data.filter((c:any)=>c.country_name===this.service.proveedorTemplate.direccion.pais && c.state_name===this.service.proveedorTemplate.direccion.provincia))
  }
}

