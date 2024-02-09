import { Component, OnInit } from '@angular/core';
import { SuppliersService } from 'src/app/services/suppliers.service';
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
import Swal from 'sweetalert2';

@Component({
  selector: 'app-suppliers-form',
  templateUrl: './suppliers-form.component.html',
  styleUrls: ['./suppliers-form.component.css']
})
export class SuppliersFormComponent implements OnInit {

  idParam = this.route.snapshot.paramMap.get("id-supplier");

  isEditSession: boolean = this.idParam !== null;

  // rubrosPermitidos = Object.values(supplierCategory)
  taxConditions: TaxCondition[] = []


  supplier: any =
    {
      "supCode": "",
      "field": {
        "fieldId": ''
      },
      "supImage": "",
      "supWebsite": "",
      "supEmail": "",
      "supTelephone": "",
      "address": {
        "state": {
          "stateId": '',
          "country": {
            "counId": '',
          },
        },
        "cityName": "",
        "addrPostcode": "",
        "addrStreet": "",
        "addrNumber": '',
      },
      "supCuit": "",
      "taxCond": {
        "taxId": '',
      },
      "supContact": {
        "supContactName": "",
        "supContactLastname": "",
        "supContactTelephone": '',
        "supContactEmail": "",
        "supContactRole": "",
      },
      "isActive":true
    }



  constructor(
    public supplierService: SuppliersService,
    private route: ActivatedRoute,
    public geo: LocalizationService,
    private location: Location,
    private router: Router,
    private taxConditionService: TaxConditionsService,
    private fieldService: FieldsService
  ) { }

  countries: Country[] = []
  states: State[] = []
  fields: Field[] = []



  getBack() {
    this.location.back()
  }

  ngOnInit(): void {
    this.getCountries()
    this.getFields();
    this.getTaxConditions();
    if (this.isEditSession) {
      this.getSuppliers();

      this.supplierService.getSupplierById(this.idParam!).subscribe(
        {
          next: (data: HttpResponse<Supplier[]>) => {
            this.supplier = data.body!;
            this.getStates(this.supplier.address.state.country.counId)

          },
          error: (error: any) => console.log("error")
        }
      )
    }

  }

  getSuppliers() {
    this.supplierService.getSupplierById(this.idParam!).subscribe(
      {
        next: (data: HttpResponse<Supplier>) => { this.supplier = data.body! },
        error: (error: any) => console.log("error")
      }
    )
  }

  getFields() {
    this.fieldService.getFields().subscribe(
      {
        next: (data: HttpResponse<Field[]>) => { this.fields = data.body! },
        error: (error: any) => console.log("error")
      }
    )
  }

  getTaxConditions() {
    this.taxConditionService.getTaxConditions().subscribe(
      {
        next: (data: HttpResponse<TaxCondition[]>) => { this.taxConditions = data.body! },
        error: (error: any) => console.log("error")
      }
    )
  }

  createProveedor(form: NgForm) {
    if (this.isEditSession) {
      this.supplierService.editSupplier(this.supplier).subscribe(
        {
          next: (data) => {
            Swal.fire({
              position: "center",
              icon: "success",
              title: data,
  
              showConfirmButton: false,
              timer: 1500
            })
            setTimeout(() => this.router.navigateByUrl('/suppliers'), 1000)
          },
          error: (error) => {
            Swal.fire({
              position: "center",
              icon: "error",
              title: error.error,
  
              showConfirmButton: false,
              timer: 1500
            })
          }
        }
      )

    }



    else this.supplierService.addSupplier(this.supplier).subscribe(
      {
        next: (data) => {
          Swal.fire({
            position: "center",
            icon: "success",
            title: data,

            showConfirmButton: false,
            timer: 1500
          })
          setTimeout(() => this.router.navigateByUrl('/suppliers'), 1000)
        },
        error: (error) => {
          console.log(error)
          Swal.fire({
            position: "center",
            icon: "error",
            title: error.error,

            showConfirmButton: false,
            timer: 1500
          })
        }
      }
    )

  }

  getCountries() {
    this.states = [];

    this.geo.getCountries().subscribe({
      next: (data: HttpResponse<Country[]>) => { this.countries = data.body! },
      error: (error) => console.error(error)
    })
  }

  getStates(countryId: number) {

    this.geo.getStates(countryId).subscribe({
      next: (data: HttpResponse<State[]>) => { this.states = data.body! },
      error: (error) => console.error(error)
    })
  }

}

