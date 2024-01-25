import { proveedorItem } from "./assets/utils/utils.js";

import { getProveedores } from "./assets/utils/handle-storage.js";
// import proveedor from './assets/data/proveedores.js'

const proveedores = getProveedores()

const proveedoresContainer = document.getElementById("proveedores-container")
proveedores.forEach( p => proveedoresContainer.appendChild(proveedorItem(p)))