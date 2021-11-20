package com.sd

import com.sd.beans.proveedor.ProveedorB
import com.sd.service.proveedor.IProveedorService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProveedorController {

    //services
    IProveedorService proveedorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer id) {
        def page = Math.max(id?:0,0)
        def proveedores = proveedorService.getAll(page)

        [proveedorInstanceList: proveedores.getList(), proveedorIntanceTotal: proveedores.getTotal()]
    }

    def create() {
        [proveedorInstance: new ProveedorB(params)]
    }

    def save() {
        def proveedorInstance = new ProveedorB(params)
        def newProveedor = proveedorService.save(proveedorInstance)
        if (!newProveedor?.getId()) {
            render(view: "create", model: [proveedorInstance: proveedorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [
                message(code: 'proveedor.label', default: 'Proveedor'),
                newProveedor.getId()
        ])
        redirect(action: "list")
    }

    def update(Integer id) {
        def proveedorInstance = new ProveedorB(params)
        if (id != proveedorInstance.getId()) {
            flash.message = message(code: 'default.not.equal.message', args: [
                    message(code: 'proveedor.label', default: 'Proveedor'),
                    id
            ])
        }

        def newProveedor = proveedorService.update(id, proveedorInstance)
        if (!newProveedor?.getId()) {
            render(view: "create", model: [proveedorInstance: proveedorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [
                message(code: 'proveedor.label', default: 'Proveedor'),
                newProveedor.getId()
        ])
        redirect(action: "list")
    }

    def show() { // si no encuentra deberia llevar a la pag de not found 404
        def id = Integer.valueOf(params['id'])
        def proveedorInstance = proveedorService.getById(id.toInteger())
        [proveedorInstance: proveedorInstance]
    }

    def edit() {
        def id = Integer.valueOf(params['id'])
        def proveedorInstance = proveedorService.getById(id.toInteger())
        if (!proveedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'proveedor.label', default: 'Proveedor'),
                    id
            ])
            redirect(action: "list")
            return
        }

        [proveedorInstance: proveedorInstance]
    }

    def delete() {
        def id = Integer.valueOf(params['id'])
        def proveedorInstance = proveedorService.getById(id)
        if (!proveedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'proveedor.label', default: 'Proveedor'),
                    id
            ])
            redirect(action: "list")
            return
        }

        proveedorService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [
                message(code: 'proveedor.label', default: 'Producto'),
                id
        ])
        redirect(action: "list")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
