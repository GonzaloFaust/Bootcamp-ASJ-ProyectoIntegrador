import { OrderState } from "./orderState"

export interface Order {
    numero_orden_compra:string,
    state:OrderState,
    fecha_emision: string,
    fecha_entrega_esperada: string,
    informacion_recepcion: {
        direccion: {
            calle_numero: string,
            cp: string,
            localidad: string,
            provincia: string,
            pais: string
        }
    },
    cod_proveedor: string,
    productos: [
        {
            codigo_SKU: string,
            cantidad: number
        } 
    ]
  }