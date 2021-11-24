package com.sd

import com.sd.beans.facturaCompraDetalle.FacturaCompraDetalleB
import com.sd.service.facturaCompra.IFacturaCompraService
import com.sd.service.facturaCompraDetalle.IFacturaCompraDetalleService
import com.sd.service.producto.IProductoService

import static org.springframework.http.HttpStatus.*

class FacturaCompraDetalleController {

    //services
    IFacturaCompraDetalleService facturaCompraDetalleService
    IFacturaCompraService facturaCompraService
    IProductoService productoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer id) {
        def page = Math.max(id?:0,0)
        def facturasCompraDetalle = facturaCompraDetalleService.getAll(page)
        [facturaCompraDetalleInstanceList: facturasCompraDetalle.getList(),
         facturaCompraDetalleIntanceTotal: facturasCompraDetalle.getTotal(),
         totalPages:facturasCompraDetalle.getTotalPages(),
         currentPage:facturasCompraDetalle.getPage()]
    }

    def create() {
        def facturasDetalle = facturaCompraService.getAll(0)
        def productos = productoService.getAll(1)
        [facturaCompraDetalleInstance: new FacturaCompraDetalleB(params),
         facturasCompra: facturasDetalle.getList(),
         productos: productos.getList()]
    }

    def save() {
        def facturaCompraDetalleInstance = new FacturaCompraDetalleB(params)
        facturaCompraDetalleInstance.setProducto(productoService.getById(Integer.valueOf(params.productoId)))
        facturaCompraDetalleInstance.setFactura(facturaCompraService.getById(Integer.valueOf(params.facturaCompraId)))
        facturaCompraDetalleInstance.setSubtotal(0);
        def newFacturaCompraDetalle = facturaCompraDetalleService.save(facturaCompraDetalleInstance)
        if (!newFacturaCompraDetalle?.getId()) {
            render(view: "create", model: [facturaCompraDetalleInstance: facturaCompraDetalleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [
                message(code: 'facturaCompraDetalle.label', default: 'Factura Compra Detalle'),
                newFacturaCompraDetalle.getId()
        ])
        redirect(action: "list")
    }

    def update(Integer id) {
        def facturaCompraDetalleInstance = new FacturaCompraDetalleB(params)
        facturaCompraDetalleInstance.setProducto(productoService.getById(Integer.valueOf(params.productoId)))
        facturaCompraDetalleInstance.setFactura(facturaCompraService.getById(Integer.valueOf(params.facturaCompraId)))
        def cantidad = facturaCompraDetalleInstance.getCantidad();
        def costo = facturaCompraDetalleInstance.getProducto().getCosto();
        facturaCompraDetalleInstance.setSubtotal(cantidad * costo );
        if (id != facturaCompraDetalleInstance.getId()) {
            flash.message = message(code: 'default.not.equal.message', args: [
                    message(code: 'facturaCompraDetalle.label', default: 'Factura Compra Detalle'),
                    id
            ])
        }

        def newFacturaCompraDetalle = facturaCompraDetalleService.update(id, facturaCompraDetalleInstance)
        if (!newFacturaCompraDetalle?.getId()) {
            render(view: "create", model: [facturaCompraDetalleInstance: facturaCompraDetalleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [
                message(code: 'facturaCompraDetalle.label', default: 'Factura Compra Detalle'),
                newFacturaCompraDetalle.getId()
        ])
        redirect(action: "list")
    }

    def show() { // si no encuentra deberia llevar a la pag de not found 404
        def id = Integer.valueOf(params['id'])
        def facturaCompraDetalleInstance
        def producto
        def facturaCompra
        try {
            facturaCompraDetalleInstance = facturaCompraDetalleService.getById(id.toInteger())
            producto = facturaCompraDetalleInstance.getProducto()
            facturaCompra = facturaCompraDetalleInstance.getFactura()
        } catch (Exception ex) {
            redirect(action: "notFound")
        }
        [facturaCompraDetalleInstance: facturaCompraDetalleInstance,
         productos: producto,
         facturasCompra: facturaCompra]
    }

    def edit() {
        def id = Integer.valueOf(params['id'])
        def facturaCompraDetalleInstance = facturaCompraDetalleService.getById(id.toInteger())
        def productos = productoService.getAll(1)
        def facturasCompra = facturaCompraService.getAll(0)
        if (!facturaCompraDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'facturaCompraDetalle.label', default: 'Factura Compra Detalle'),
                    id
            ])
            redirect(action: "list")
            return
        }


        [facturaCompraDetalleInstance: facturaCompraDetalleInstance,
         productos: productos.getList(),
         facturasCompra: facturasCompra.getList()]
    }

    def delete() {
        def id = Integer.valueOf(params['id'])
        def facturaCompraDetalleInstance = facturaCompraDetalleService.getById(id)
        if (!facturaCompraDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'facturaCompraDetalle.label', default: 'Factura Compra Detalle'),
                    id
            ])
            redirect(action: "list")
            return
        }

        facturaCompraDetalleService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [
                message(code: 'facturaCompraDetalle.label', default: 'Factura Compra Detalle'),
                id
        ])
        redirect(action: "list")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaCompraDetalle.label', default: 'Factura Compra Detalle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
