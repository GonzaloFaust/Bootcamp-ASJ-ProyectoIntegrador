import { ordenItem } from "./assets/utils/utils.js";

// import { getOrdenes } from "./assets/utils/handle-storage";
import ordenCompra from './assets/data/ordenes.js'

const ordenes = ordenCompra

const ordenesContainer = document.getElementById("ordenes-container")
ordenes.forEach( o => ordenesContainer.appendChild(ordenItem(o)))