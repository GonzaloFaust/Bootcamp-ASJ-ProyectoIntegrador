const ordenCompra =
    [{
        numeroOrdenCompra: "OC001",
        fechaEmision: "2023-01-15",
        fechaEntregaEsperada: "2023-02-01",
        informacionRecepcion: {
            direccion: {
                calleNumero: "Calle Principal 456",
                cp: "54321",
                localidad: "Ciudad",
                provincia: "Provincia",
                pais: "País"
            }
        },
        codProveedor: "PROD001",
        productos: [
            {
                codigoSKU: "SKU123",
                cantidad: 5
            },
            {
                codigoSKU: "SKU456",
                cantidad: 3
            }
        ]
    }];
export default ordenCompra