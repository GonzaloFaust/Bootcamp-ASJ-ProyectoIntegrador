
import { supplierCategory } from "./supplierCategory"
export interface Supplier {
    codigo: string,
    razon_social: string,
    rubro: supplierCategory,
    sitio_web: string,
    email: string,
    telefono: string,
    direccion: {
      calle_numero: string,
      cp: string,
      localidad: string,
      provincia: string,
      pais: string
    },
    datos_fiscales: {
      cuit: string,
      condicion_iva: string
    },
    datos_contacto: {
      nombre: string,
      apellido: string,
      telefono_contacto: string,
      email_contacto: string,
      rol: string
    },
    active:boolean
  }