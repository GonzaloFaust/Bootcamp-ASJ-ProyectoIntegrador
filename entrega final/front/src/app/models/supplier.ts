
import { Address } from "./address"
import { Field } from "./fields"
import { SupplierContact } from "./supplier-contact"
import { TaxCondition } from "./taxCondition"
export interface Supplier {
  "supId": number,
  "supCode": string,
  "supBussinessName": string,
  "field": Field,
  "supImage": string,
  "supWebsite": string,
  "supEmail": string,
  "supTelephone": string,
  "address": Address,
  "supCuit": string,
  "taxCond": TaxCondition,
  "supContact": SupplierContact,
  "isActive": boolean,
}