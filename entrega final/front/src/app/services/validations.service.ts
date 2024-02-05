import { Injectable } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';

import { Field } from '../models/fields';
import { TaxCondition } from '../models/taxCondition';


@Injectable({
  providedIn: 'root'
})
export class ValidationsService {

  constructor() {

   }

   validateProvider(form: NgForm){
    console.log(form.value)
   }

   validateProduct(form:NgForm){

   }

   validateOrder(form:NgForm){

   }

   private validateEmail(control:any) {
    const emailPattern = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (!control.value || !emailPattern.test(control.value)) {
      return { 'validEmail': false };
    }

    return { 'validEmail': true }
  }

 
  private validateCuit(control: any) {
    const cuitPattern = /\d{2}-\d{8}-\d{1}/;

    if (!control.value || !cuitPattern.test(control.value)) {
      return { 'validCuit': false };
    }

    return { 'validCuit': true };
  }

  private notEmpty(control:any){
    return {[control.key]:control.value && control.value!=""}
  }

  // private isValidRubro(control:FormControl){
  //   return {validRubro: Object.values(supplierCategory).includes(control.value)}
  // }

  // private isValidCondicionIva(control:FormControl){
  //   return {validCondicion: Object.values(taxCondition).includes(control.value)}
  // }
}
