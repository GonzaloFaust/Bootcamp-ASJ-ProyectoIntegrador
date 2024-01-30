import { Component, OnInit } from '@angular/core';
import { SuppliersService, blankProvider } from 'src/app/services/suppliers.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { supplierCategory } from 'src/app/models/supplierCategory';
import { CondicionIva } from 'src/app/models/taxCondition';
import { LocalizationService } from 'src/app/services/localization.service';
import { Location } from '@angular/common'

@Component({
  selector: 'app-suppliers-form',
  templateUrl: './suppliers-form.component.html',
  styleUrls: ['./suppliers-form.component.css']
})
export class SuppliersFormComponent implements OnInit {

  idParam = this.route.snapshot.paramMap.get("id-proveedor");

  isEditSession: boolean = this.idParam !== null;

  rubrosPermitidos = Object.values(supplierCategory)
  condicionesIVA = Object.values(CondicionIva)

  constructor(public service: SuppliersService, private route: ActivatedRoute, public geo: LocalizationService, private location:Location, private router:Router) { }

  countries: any[] = []
  states: any[] = []
  cities: any[] = []

  getBack(){
    this.location.back()
  }

  ngOnInit(): void {
    this.getCountries()
    if (this.isEditSession) {
      let prov = this.service.getSupplierById(this.idParam!);
      this.service.supplierTemplate.direccion.pais = prov.direccion.pais;
      this.getStates()
      this.service.supplierTemplate.direccion.provincia = prov.direccion.provincia;
      this.getCities()
      this.service.supplierTemplate.direccion.localidad = prov.direccion.localidad;


    }
    else this.service.supplierTemplate = structuredClone(blankProvider)
  }

  createProveedor(form:NgForm) {
    if (this.isEditSession) this.service.editSupplier(this.idParam!)
    else this.service.addSupplier()
    setTimeout(()=>this.router.navigateByUrl('/proveedores'),1000)
  }

  getCountries() {
    this.states = [];

    this.geo.getCountries().subscribe((data: any) => this.countries = data)
  }

  getStates() {
    this.cities = [];
    this.geo.getStates().subscribe((data: any) => this.states = data.filter((s: any) => s.country_name === this.service.supplierTemplate.direccion.pais))
  }

  getCities() {
    this.geo.getCities().subscribe((data: any) => this.cities = data.filter((c: any) => c.country_name === this.service.supplierTemplate.direccion.pais && c.state_name === this.service.supplierTemplate.direccion.provincia))
  }
}

