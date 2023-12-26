import { Rubro } from "../../app/models/rubro";

const proveedor = [
    {
        codigo: "1",
        razon_social: "Proveedor ABC S.A.",
        rubro: "Electrónica" as Rubro,
        sitio_web: "http://www.proveedorabc.com",
        email: "info@proveedorabc.com",
        telefono: "123-456-789",
        direccion: {
            calle_numero: "Calle Principal 123",
            cp: "12345",
            localidad: "Ciudad",
            provincia: "Provincia",
            pais: "Pais"
        },
        datos_fiscales: {
            cuit: "30-12345678-9",
            condicion_iva: "Responsable Inscripto"
        },
        datos_contacto: {
            nombre: "Juan",
            apellido: "Pérez",
            telefono_contacto: "987-654-321",
            email_contacto: "juan.perez@proveedorabc.com",
            rol: "Gerente de Ventas"
        }
    }]
export { proveedor };