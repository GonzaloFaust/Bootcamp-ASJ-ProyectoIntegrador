import {insertProveedor } from "./assets/utils/handle-storage.js";


const guardar = document.getElementById("guardar-proveedor")


guardar.addEventListener("click", (event) => {
    event.preventDefault();

    const razon_social = document.getElementById('razon_social').value;
    const sitio_web = document.getElementById('sitio_web').value;
    const email = document.getElementById('email').value;
    const telefono = document.getElementById('telefono').value;
    const calle_numero = document.getElementById('calle_numero').value;
    const cp = document.getElementById('cp').value;
    const localidad = document.getElementById('localidad').value;
    const provincia = document.getElementById('provincia').value;
    const cuit = document.getElementById('cuit').value;
    const condicion_iva = document.getElementById('condicion_iva').value;
    const nombre_contacto = document.getElementById('nombre_contacto').value;
    const apellido_contacto = document.getElementById('apellido_contacto').value;
    const telefono_contacto = document.getElementById('telefono_contacto').value;
    const email_contacto = document.getElementById('email_contacto').value;
    const rol_contacto = document.getElementById('rol_contacto').value;

    const proveedor = {
        razonSocial: razon_social,
        sitioWeb: sitio_web,
        email: email,
        telefono: telefono,
        direccion: {
            calleNumero: calle_numero,
            cp: cp,
            localidad: localidad,
            provincia: provincia
        },
        datosFiscales:{
            cuit: cuit,
        condicionIva: condicion_iva,
        },        
        contacto: {
            nombre: nombre_contacto,
            apellido: apellido_contacto,
            telefono: telefono_contacto,
            email: email_contacto,
            rol: rol_contacto
        }
    };

    insertProveedor(proveedor)
    document.getElementById('proveedor-form').reset();

    alert('Proveedor registrado y guardado en el localStorage.');
})