import { ordenItem } from "./assets/utils/utils.js";

// import { getOrdenesCompra } from "./assets/utils/handle-storage";
import ordenCompra from './assets/data/ordenes.js' //aca queda con esto por que se dificulto mucho agregar cosas al storage

const ordenes = ordenCompra

const ordenesContainer = document.getElementById("ordenes-container")
ordenes.forEach( o => ordenesContainer.appendChild(ordenItem(o)))