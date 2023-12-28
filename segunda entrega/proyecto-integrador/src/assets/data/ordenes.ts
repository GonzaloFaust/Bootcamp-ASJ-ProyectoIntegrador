
const ordenCompra =
    [
        {
            numero_orden_compra: "OC001",
            fecha_emision: "2023-01-15",
            fecha_entrega_esperada: "2023-02-01",
            state:"confirmado",
            informacion_recepcion: {
                direccion: {
                    calle_numero: "Calle Principal 456",
                    cp: "54321",
                    localidad: "Ciudad",
                    provincia: "Provincia",
                    pais: "Pais"
                }
            },
            cod_proveedor: "PROV0",
            productos: [
                {
                    codigo_SKU: "SKU123",
                    cantidad: 5
                },
                {
                    codigo_SKU: "SKU456",
                    cantidad: 3
                }
            ]
        }
    ];
export { ordenCompra }