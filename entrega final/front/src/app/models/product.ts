import { productCategory } from "./productCategory"

export interface Product {
    id:string,
    cod_proveedor: string,
    codigo_SKU: string,
    categoria: productCategory,
    image:string,
    nombre_producto: string,
    descripcion: string,
    precio: number
  
  }