package com.sd

import com.sd.beans.facturaCompraDetalle.FacturaCompraDetalleB
import com.sd.service.facturaCompra.IFacturaCompraService
import com.sd.service.facturaCompraDetalle.IFacturaCompraDetalleService
import com.sd.service.producto.IProductoService
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class FacturaCompraDetalleController {

    //services
    IFacturaCompraDetalleService facturaCompraDetalleService
    IFacturaCompraService facturaCompraService
    IProductoService productoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "notFound")
    }

    def create(Integer id) {    //CORREGIDO
        def detalle = new FacturaCompraDetalleB(params)
        def factura = facturaCompraService.getById(id)
        def productos = productoService.getAll(1)
        detalle.setFactura(factura)
        [detalle: detalle, productos: productos.getList(), from: params['from']]
    }

    def save() {    //CORREGIDO
        def detalle = new FacturaCompraDetalleB(params)
        def producto = productoService.getById(Integer.valueOf(params.productoId))
        def factura = facturaCompraService.getById(Integer.valueOf(params.facturaId))
        detalle.setProducto(producto)
        detalle.setFactura(factura)
        detalle.setSubtotal(producto.getCosto() * detalle.getCantidad())
        factura.setTotal(factura.getTotal() + detalle.getSubtotal())
        facturaCompraDetalleService.save(detalle)
        facturaCompraService.update(factura.getId(), factura)
        redirect(action: "show", controller: "facturaCompra", params: [id: factura.getId()])
    }

    def edit() {    //CORREGIDO
        def id = Integer.valueOf(params['id'])
        def detalle = facturaCompraDetalleService.getById(id)
        def productos = productoService.getAll(1)
        [detalle: detalle, productos: productos.getList()]
    }

    def update() {  //CORREGIDO
        def id = Integer.valueOf(params['id'])
        FacturaCompraDetalleB detalleAnterior
        try {
            detalleAnterior = facturaCompraDetalleService.getById(id)
        } catch(Exception ex){ redirect(action: "notFound") }
        def detalle = new FacturaCompraDetalleB(params)
        def producto = productoService.getById(Integer.valueOf(params.productoId))
        def factura = detalleAnterior.getFactura()
        detalle.setProducto(producto)
        detalle.setFactura(factura)
        detalle.setSubtotal(producto.getCosto() * detalle.getCantidad())
        factura.setTotal((factura.getTotal() - detalleAnterior.getSubtotal()) + detalle.getSubtotal())
        facturaCompraDetalleService.update(detalle.getId(), detalle)
        facturaCompraService.update(factura.getId(), factura)
        redirect(action: "edit", controller: "facturaCompra", params: [id: factura.getId()])
    }

    def delete() {  //CORREGIDO
        def id = Integer.valueOf(params['id'])
        def detalle = facturaCompraDetalleService.getById(id)
        def factura = detalle.getFactura()
        factura.setTotal(factura.getTotal() - detalle.getSubtotal())
        facturaCompraDetalleService.delete(id)
        facturaCompraService.update(factura.getId(), factura)
        redirect(action: "edit", controller: "facturaCompra", params: [id: factura.getId()])
    }

    protected void notFound() {
        request.withFormat {
            '*'{ render status: NOT_FOUND }
        }
    }
}
