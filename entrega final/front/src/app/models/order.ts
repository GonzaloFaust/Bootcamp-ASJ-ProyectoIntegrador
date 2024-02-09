import { Address } from "./address"
import { OrderStatus } from "./orderStatus"
import { Supplier } from "./supplier"

export interface Order {
        "ordId":number,
        "ordStatus":OrderStatus,
        "ordIssueDate":string,
        "ordExpDeliverDate":string,
        "address":Address,
        "supplier":Supplier        
  }