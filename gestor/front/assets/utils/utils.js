

function productCard(producto) {
    const card = document.createElement('div');
    card.className = 'card';
    card.style.width = '18rem';

    const cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    const cardTitle = document.createElement('h5');
    cardTitle.className = 'card-title';
    cardTitle.textContent = producto.nombreProducto;

    const cardSubtitle = document.createElement('h6');
    cardSubtitle.className = 'card-subtitle mb-2 text-body-secondary';
    cardSubtitle.textContent = producto.categoria;

    const cardText = document.createElement('p');
    cardText.className = 'card-text';
    cardText.textContent = producto.descripcion;

    const cardPrice = document.createElement('p');
    cardPrice.className = 'card-text';
    cardPrice.innerHTML = '<strong>Precio: $' + producto.precio.toFixed(2) + '</strong>';

    const [btnEdit, btnDelete] = actionButtons()

    cardBody.appendChild(cardTitle);
    cardBody.appendChild(cardSubtitle);
    cardBody.appendChild(cardText);
    cardBody.appendChild(cardPrice);
    cardBody.appendChild(btnEdit);
    cardBody.appendChild(btnDelete);

    card.appendChild(cardBody);
    return card;
}

function proveedorItem(orden) {
    const tableRow = document.createElement('tr')
    const tableColumnCod = document.createElement('td')
    const tableColumnRaz = document.createElement('td')
    const tableColumnWeb = document.createElement('td')
    const tableColumnMail = document.createElement('td')
    const tableColumnTel = document.createElement('td')
    const tableColumnDir = document.createElement('td')
    const tableColumnFis = document.createElement('td')
    const tableColumnAcc = document.createElement('td')

    tableColumnCod.textContent = orden.codigo
    tableColumnRaz.textContent = orden.razonSocial
    tableColumnWeb.textContent = orden.sitioWeb
    tableColumnMail.textContent = orden.email
    tableColumnTel.textContent = orden.telefono
    tableColumnDir.textContent = `${orden.direccion.calleNumero}, ${orden.direccion.cp}, ${orden.direccion.localidad}, ${orden.direccion.provincia}, ${orden.direccion.pais}`
    tableColumnFis.textContent = `${orden.datosFiscales.cuit}, ${orden.datosFiscales.condicionIva}`

    const [btnEdit, btnDelete] = actionButtons()

    tableColumnAcc.appendChild(btnEdit)
    tableColumnAcc.appendChild(btnDelete)

    tableRow.appendChild(tableColumnCod)
    tableRow.appendChild(tableColumnRaz)
    tableRow.appendChild(tableColumnWeb)
    tableRow.appendChild(tableColumnMail)
    tableRow.appendChild(tableColumnTel)
    tableRow.appendChild(tableColumnDir)
    tableRow.appendChild(tableColumnFis)
    tableRow.appendChild(tableColumnAcc)

    return tableRow;
}

function ordenItem(orden) {
const tableRow = document.createElement('tr')
    const tableColumnNroOrden = document.createElement('td')
    const tableColumnFechaEmit = document.createElement('td')
    const tableColumnProv = document.createElement('td')
    const tableColumnItems = document.createElement('td')
    const tableColumnTotal= document.createElement('td')

    const tableColumnAcc = document.createElement('td')

    tableColumnNroOrden.textContent = orden.cnumeroOrdenCompra
    tableColumnFechaEmit.textContent = orden.fechaEmision
    tableColumnProv.textContent = orden.codProveedor
    tableColumnItems.textContent = orden.productos.reduce((acc,p) => acc+= p.cantidad,0)
    tableColumnTotal.textContent = "$900223"//texto de prueba xq tendria que relacionar con los productos y el precio y no me da el tiempo

    const [btnEdit, btnDelete] = actionButtons()

    tableColumnAcc.appendChild(btnEdit)
    tableColumnAcc.appendChild(btnDelete)

    tableRow.appendChild(tableColumnNroOrden)
    tableRow.appendChild(tableColumnFechaEmit)
    tableRow.appendChild(tableColumnProv)
    tableRow.appendChild(tableColumnItems)
    tableRow.appendChild(tableColumnTotal)
    tableRow.appendChild(tableColumnAcc)

    return tableRow;
}

function actionButtons(){
    const btnEdit = document.createElement('button');
    btnEdit.className = 'btn btn-circle btn-sm bg-gradient-warning';
    btnEdit.innerHTML = '<i class="fas fa-pen-square text-white"></i>'

    const btnDelete = document.createElement('button');
    btnDelete.className = 'btn btn-circle btn-sm bg-gradient-danger ml-1';
    btnDelete.innerHTML = '<i class="fas fa-trash text-white"></i>'

    return [btnEdit, btnDelete]

}

export { productCard, proveedorItem, ordenItem }