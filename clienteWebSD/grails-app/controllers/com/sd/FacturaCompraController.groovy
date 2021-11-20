package com.sd

import com.sd.beans.facturaCompra.FacturaCompraB
import com.sd.beans.proveedor.ProveedorB
import com.sd.beans.usuario.UsuarioB
import com.sd.service.facturaCompra.IFacturaCompraService
import com.sd.service.proveedor.IProveedorService
import com.sun.jersey.api.client.Client
import grails.validation.ValidationException

import java.text.SimpleDateFormat

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

        [facturaCompraInstanceList: facturasCompra.getList(), facturaCompraIntanceTotal: facturasCompra.getTotal(),
         totalPages:facturasCompra.getTotalPages(), currentPage:facturasCompra.getPage()]
    }

    def create() {
        def proveedores = proveedorService.getAll(0)
        [facturaCompraInstance: new FacturaCompraB(params), proveedores: proveedores.getList()]
    }

    def save() {
        def facturaCompraInstance = new FacturaCompraB(params)
        facturaCompraInstance.setFecha(new Date(Integer.parseInt(params.fecha_year) - 1900, Integer.parseInt(params.fecha_month), Integer.parseInt(params.fecha_day)))
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

    def update(Integer id) {
        def facturaCompraInstance = new FacturaCompraB(params)
        facturaCompraInstance.setFecha(new Date(Integer.parseInt(params.fecha_year) - 1900, Integer.parseInt(params.fecha_month), Integer.parseInt(params.fecha_day)))
        facturaCompraInstance.setProveedor(proveedorService.getById(Integer.valueOf(params.proveedorId)))
        if (id != facturaCompraInstance.getId()) {
            flash.message = message(code: 'default.not.equal.message', args: [
                    message(code: 'facturaCompra.label', default: 'Factura Compra'),
                    id
            ])
        }

        def newFacturaCompra = facturaCompraService.update(id, facturaCompraInstance)
        if (!newFacturaCompra?.getId()) {
            render(view: "create", model: [facturaCompraInstance: facturaCompraInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [
                message(code: 'facturaCompra.label', default: 'Factura Compra'),
                newFacturaCompra.getId()
        ])
        redirect(action: "list")
    }

    def show() { // si no encuentra deberia llevar a la pag de not found 404
        def id = Integer.valueOf(params['id'])
        def facturaCompraInstance = facturaCompraService.getById(id.toInteger())
        System.out.println("FECHA: " + params.fecha + " " + params['fecha'])
        [facturaCompraInstance: facturaCompraInstance, proveedores:facturaCompraInstance.getProveedor()]
    }

    def edit() {
        def id = Integer.valueOf(params['id'])
        def facturaCompraInstance = facturaCompraService.getById(id.toInteger())
        def proveedores = proveedorService.getAll(0)
        if (!facturaCompraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'facturaCompra.label', default: 'Factura Compra'),
                    id
            ])
            redirect(action: "list")
            return
        }


        [facturaCompraInstance: facturaCompraInstance, proveedores: proveedores.getList()]
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
