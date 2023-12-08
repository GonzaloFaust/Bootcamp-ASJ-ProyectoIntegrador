function getProveedores(){
    const prov = localStorage.getItem("proveedores")
    return JSON.parse(prov) || [];
}

function getProductos(){
    const prod = localStorage.getItem("productos")
    return JSON.parse(prod) || [];
}

function getOrdenesCompra(){
    const ord = localStorage.getItem("ordenes")
    return JSON.parse(ord) || [];
}

function insertProveedor(item){
    const proveedores = getProveedores()
    proveedores.push(item)
   localStorage.setItem("proveedores", JSON.stringify(proveedores))
}

function insertProducto(item){
   const productos = getProductos()
   productos.push(item)
   localStorage.setItem("productos", JSON.stringify(productos))
}

function insertOrdenCompra(item){
    const ordenes = getOrdenes()
   ordenes.push(item)
   localStorage.setItem("ordenes", JSON.stringify(ordenes))
}

function deleteOrden(id){
    const ordenes = getOrdenes().filter(o=>o.numeroOrdenCompra!==id)
    localStorage.setItem("ordenes", JSON.stringify(ordenes))
}
function deleteProducto(id){
    const productos = getProductos().filter(prod=>prod.codigoSKU!==id)
    localStorage.setItem("productos", JSON.stringify(productos))
}
function deleteProveedor(id){
    const proveedores = getProductos().filter(prov=>prov.codigo!==id)
    localStorage.setItem("proveedores", JSON.stringify(proveedores))
}

export {
    getProductos,
    getProveedores,
    getOrdenesCompra, 
    insertOrdenCompra,
    insertProveedor,
    insertProducto,
    deleteOrden,
    deleteProducto,
    deleteProveedor
}