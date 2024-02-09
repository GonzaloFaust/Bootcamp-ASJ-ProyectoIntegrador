import { Order } from "./order"
import { Product } from "./product"

export interface OrderDetail{
    "order": Order,
    "product": Product,
    "prodQuantity": number
}