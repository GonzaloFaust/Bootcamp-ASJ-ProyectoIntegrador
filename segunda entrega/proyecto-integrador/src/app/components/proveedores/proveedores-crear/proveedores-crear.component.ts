import { Component, OnInit } from '@angular/core';
import { ProveedoresService, blankProvider } from 'src/app/services/proveedores.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { supplierCategory } from 'src/app/models/supplierCategory';
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

  rubrosPermitidos = Object.values(supplierCategory)
  condicionesIVA = Object.values(CondicionIva)

  constructor(public service: ProveedoresService, private route: ActivatedRoute, public geo: LocalizationService) { }

  countries: any[] = []
  states: any[] = []
  cities: any[] = []

  ngOnInit(): void {
    this.getCountries()
    if (this.isEditSession) {
      let prov = this.service.getProveedorById(this.idParam!);
      this.service.proveedorTemplate.direccion.pais = prov.direccion.pais;
      this.getStates()
      this.service.proveedorTemplate.direccion.provincia = prov.direccion.provincia;
      this.getCities()
      this.service.proveedorTemplate.direccion.localidad = prov.direccion.localidad;


    }
    else this.service.proveedorTemplate = structuredClone(blankProvider)
  }

  createProveedor(form: NgForm) {
    if (this.isEditSession) this.service.editProveedor(this.idParam!)
    else this.service.addProveedor(form)

  }

  getCountries() {
    this.states = [];

    this.geo.getCountries().subscribe((data: any) => this.countries = data)
  }

  getStates() {
    this.cities = [];
    this.geo.getStates().subscribe((data: any) => this.states = data.filter((s: any) => s.country_name === this.service.proveedorTemplate.direccion.pais))
  }

  getCities() {
    this.geo.getCities().subscribe((data: any) => this.cities = data.filter((c: any) => c.country_name === this.service.proveedorTemplate.direccion.pais && c.state_name === this.service.proveedorTemplate.direccion.provincia))
  }
}

