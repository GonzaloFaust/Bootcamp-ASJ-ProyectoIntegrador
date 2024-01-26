import { insertOrden } from "./assets/utils/handle-storage.js";


const guardar = document.getElementById("guardar-orden")


guardar.addEventListener("click", (event) => {
    event.preventDefault();

    // const codProveedor = document.getElementById('codProveedor').value;
    // const codigoSKU = document.getElementById('codigoSKU').value;
    // const categoria = document.getElementById('categoria').value;
    // const nombreProducto = document.getElementById('nombreProducto').value;
    // const descripcion = document.getElementById('descripcion').value;
    // const precio = parseFloat(document.getElementById('precio').value);

    const orden= {
        numeroOrdenCompra: "bien gracias" //aca se puso infinitamente mas complicado por la forma en que lo plantee
    };

    insertOrden(orden)
    document.getElementById('orden-form').reset();

    alert('Producto registrado y guardado en el localStorage.');
})
