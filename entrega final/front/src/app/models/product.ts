import { Category } from "./category"
import { Supplier } from "./supplier"

export interface Product {

  "prodId": number,
  "prodSku": string,
  "supplier": Supplier,
  "category": Category,
  "prodImage": string,
  "prodName": string,
  "prodDescription": string,
  "prodPrice": number,
  "createdAt": string,
  "updatedAt": string,
  "prodAvailable": boolean
}