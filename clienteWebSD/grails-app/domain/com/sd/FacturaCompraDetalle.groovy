package com.sd

class FacturaCompraDetalle {

    Integer cantidad;
    Double subtotal;

    static belongsTo = [producto: Producto, factura: FacturaCompra]

    static constraints = {
    }
}
