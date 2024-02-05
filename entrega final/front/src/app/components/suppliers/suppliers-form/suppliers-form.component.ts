import { Component, OnInit } from '@angular/core';
import { SuppliersService} from 'src/app/services/suppliers.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Field } from 'src/app/models/fields';
import { TaxCondition } from 'src/app/models/taxCondition';
import { LocalizationService } from 'src/app/services/localization.service';
import { Location } from '@angular/common'
import { HttpResponse } from '@angular/common/http';
import { Country } from 'src/app/models/country';
import { State } from 'src/app/models/state';
import { TaxConditionsService } from 'src/app/services/tax-conditions.service';
import { Supplier } from 'src/app/models/supplier';
import { FieldsService } from 'src/app/services/fields.service';

@Component({
  selector: 'app-suppliers-form',
  templateUrl: './suppliers-form.component.html',
  styleUrls: ['./suppliers-form.component.css']
})
export class SuppliersFormComponent implements OnInit {

  idParam = this.route.snapshot.paramMap.get("id-supplier");

  isEditSession: boolean = this.idParam !== null;

  // rubrosPermitidos = Object.values(supplierCategory)
 taxConditions:TaxCondition[]=[]

  supplier:Supplier=
    {
      "supId": 0,
      "supCode": "",
      "supBussinessName": "",
      "field": {
          "fieldId": 0,
          "fieldName": "",
          "fieldDetail": "",
      },
      "supImage": "",
      "supWebsite": "",
      "supEmail": "",
      "supTelephone": "",
      "address": {
          "addrId": 0,
          "state": {
              "stateId": 0,
              "country": {
                  "counId": 0,
                  "counName": ""
              },
              "stateName": ""
          },
          "cityName": "",
          "addrPostcode": "",
          "addrStreet": "",
          "addrNumber": 0,
          "addrFloor": 0,
          "addrApartment": ""
      },
      "supCuit": "",
      "taxCond": {
          "taxId": 0,
          "taxCondTitle": ""
      },
      "supContact": {
        "supContactId": 0,
    "supContactName": "",
    "supContactLastname": "",
    "supContactTelephone": "",
    "supContactEmail": "",
    "supContactRole": "",
      },
      "isActive": true,

  }
  
 

  constructor(
    public supplierService: SuppliersService, 
    private route: ActivatedRoute, 
    public geo: LocalizationService, 
    private location:Location, 
    private router:Router,
    private taxConditionService:TaxConditionsService,
    private fieldService: FieldsService
    ) { }

  countries: Country[] = []
  states: State[] = []
  fields: Field[]=[]


  getBack(){
    this.location.back()
  }

  ngOnInit(): void {
    this.getCountries()
    if (this.isEditSession) {
      this.supplierService.getSupplierById(this.idParam!).subscribe(
        {
          next: (data: HttpResponse<Supplier>) => { this.supplier = data.body! },
          error: (error: any) => console.log("error aca guachin")
        }
      )
    //   this.supplierService.supplierTemplate.direccion.pais = prov.direccion.pais;
    //   // this.getStates()
    //   this.supplierService.supplierTemplate.direccion.provincia = prov.direccion.provincia;



    }
    else{
      this.taxConditionService.getTaxConditions().subscribe({
        next:(data:HttpResponse<TaxCondition[]>)=>{this.taxConditions=data.body!} ,
        error: (error:any)=> console.log(error)
      })
      //   this.supplierService.supplierTemplate = structuredClone(blankProvider)
      this.fieldService.getFields().subscribe({
        next:(data:HttpResponse<Field[]>)=>{this.fields=data.body!} ,
        error: (error:any)=> console.log(error)
      })
      //   this.supplierService.supplierTemplate = structuredClone(blankProvider)
    } 
  }

  createProveedor(form:NgForm) {
    // if (this.isEditSession) this.supplierService.editSupplier(this.idParam!)
    // else this.supplierService.addSupplier()
    //setTimeout(()=>this.router.navigateByUrl('/suppliers'),1000)
  }

  getCountries() {
    this.states = [];

    this.geo.getCountries().subscribe({
      next:(data:HttpResponse<Country[]>)=>{this.countries=data.body!} ,
      error: (error)=> console.error(error)
    })
  }

  getStates(countryId: number) {

    this.geo.getStates(countryId).subscribe({
      next:(data:HttpResponse<State[]>)=>{this.states=data.body!} ,
      error: (error)=> console.error(error)
    })
  }

}

