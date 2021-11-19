package com.sd

import com.sd.beans.facturaCompra.FacturaCompraB
import com.sd.beans.proveedor.ProveedorB
import com.sd.service.facturaCompra.IFacturaCompraService
import com.sd.service.proveedor.IProveedorService
import com.sun.jersey.api.client.Client
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FacturaCompraController {

    //services
    IFacturaCompraService facturaCompraService
    IProveedorService proveedorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer id) {
        def page = Math.max(id?:0,0)
        def facturasCompra = facturaCompraService.getAll(page)

        [facturaCompraInstanceList: facturasCompra, facturaCompraIntanceTotal: facturasCompra.size()]
    }

    def create() {
        def proveedores
        try {
            proveedores = proveedorService.getAll(0)
        } catch (Exception ex) {
            proveedores = new ArrayList<ProveedorB>()
        }
        [facturaCompraInstance: new FacturaCompraB(params), proveedores: proveedores]
    }

    def save() {
        def facturaCompraInstance = new FacturaCompraB(params)
        facturaCompraInstance.setProveedor(proveedorService.getById(Integer.valueOf(params.proveedorId)))
        def newFacturaCompra = facturaCompraService.save(facturaCompraInstance)
        if (!newFacturaCompra?.getId()) {
            render(view: "create", model: [facturaCompraInstance: facturaCompraInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [
                message(code: 'facturaCompra.label', default: 'Factura Compra'),
                newFacturaCompra.getId()
        ])
        redirect(action: "list")

    }

    def update() {
        def facturaCompraInstance = new FacturaCompraB(params)
        def newFacturaCompra = facturaCompraService.update(facturaCompraInstance)
        if (!newFacturaCompra?.getId()) {
            render(view: "create", model: [facturaCompraInstance: facturaCompraInstance])
            return
        }
        System.out.println("Hola Mundo");
        flash.message = message(code: 'default.updated.message', args: [
                message(code: 'facturaCompra.label', default: 'Factura Compra'),
                newFacturaCompra.getId()
        ])
        redirect(action: "list")
    }

    def show() { // si no encuentra deberia llevar a la pag de not found 404
        def id = Integer.valueOf(params['id'])
        def facturaCompraInstance = facturaCompraService.getById(id.toInteger())
        [facturaCompraInstance: facturaCompraInstance]
    }

    def edit() {
        def id = Integer.valueOf(params['id'])
        def facturaCompraInstance = facturaCompraService.getById(id.toInteger())
        if (!facturaCompraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'facturaCompra.label', default: 'Factura Compra'),
                    id
            ])
            redirect(action: "list")
            return
        }

        def proveedores
        try {
            proveedores = proveedorService.getAll(page)
        } catch (Exception ex) {
            proveedores = new ArrayList<ProveedorB>()
        }

        [facturaCompraInstance: facturaCompraInstance, proveedores: proveedores]
    }

    def delete() {
        def id = Integer.valueOf(params['id'])
        def facturaCompraInstance = facturaCompraService.getById(id)
        if (!facturaCompraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'facturaCompra.label', default: 'Factura Compra'),
                    id
            ])
            redirect(action: "list")
            return
        }

        facturaCompraService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [
                message(code: 'facturaCompra.label', default: 'Factura Compra'),
                id
        ])
        redirect(action: "list")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaCompra.label', default: 'Factura Compra'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
