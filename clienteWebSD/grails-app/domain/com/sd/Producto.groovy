package com.sd

class Producto {

    String descripcion;
    Double costo;

    static hasMany = [facturas_compra_detalle:FacturaCompraDetalle]

    static constraints = {
        descripcion blank: false, nullable: false
        costo blank: false, nullable: false
    }
}
