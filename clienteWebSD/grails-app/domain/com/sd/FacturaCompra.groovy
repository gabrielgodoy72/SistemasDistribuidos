package com.sd

class FacturaCompra {

    Date fecha;
    String numero;
    Double total;

    static belongsTo = [proveedor: Proveedor]
    static hasMany = [detalles: FacturaCompraDetalle]

    static constraints = {
    }
}
