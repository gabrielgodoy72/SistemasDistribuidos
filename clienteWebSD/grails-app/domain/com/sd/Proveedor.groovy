package com.sd

class Proveedor {

    String nombre;
    String ruc;
    String telefono;
    String direccion;

    static hasMany = [facturas_compra:FacturaCompra]

    static constraints = {
    }
}
