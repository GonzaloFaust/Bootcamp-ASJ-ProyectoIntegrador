import { insertProducto } from "./assets/utils/handle-storage.js";


const guardar = document.getElementById("guardar-proveedor")


guardar.addEventListener("click", (event) => {
    event.preventDefault();

    const codProveedor = document.getElementById('codProveedor').value;
    const codigoSKU = document.getElementById('codigoSKU').value;
    const categoria = document.getElementById('categoria').value;
    const nombreProducto = document.getElementById('nombreProducto').value;
    const descripcion = document.getElementById('descripcion').value;
    const precio = parseFloat(document.getElementById('precio').value);

    const producto = {
        codProveedor: codProveedor,
        codigoSKU: codigoSKU,
        categoria: categoria,
        nombreProducto: nombreProducto,
        descripcion: descripcion,
        precio: precio
    };

    insertProducto(producto)
    document.getElementById('producto-form').reset();

    alert('Producto registrado y guardado en el localStorage.');
})


