import { productCard } from "./assets/utils/utils.js";

import { getProductos } from "./assets/utils/handle-storage.js";
import producto from './assets/data/productos.js'

const productos = getProductos()

const productosContainer = document.getElementById("productos-container")
productos.forEach( p => productosContainer.appendChild(productCard(p)))