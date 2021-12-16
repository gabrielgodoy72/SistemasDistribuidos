package com.sd

import com.sd.beans.producto.ProductoB
import com.sd.service.producto.IProductoService
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*

class ProductoController {

    //services
    IProductoService productoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

    def paginate(Integer page){
        def id = Integer.valueOf(params['id'])
        System.out.println(id)

    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def list(Integer id) {
        def params = params.toString()
        def page = Math.max(id?:1,0)
        def productos = productoService.getAll(page)
        System.out.println("PARAMS: " + params)
        System.out.println("total pages: " + productos.getTotalPages())
        [productoInstanceList: productos.getList(), productoIntanceTotal: productos.getTotal()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        [productoInstance: new ProductoB(params)]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def productoInstance = new ProductoB(params)
        def newProducto = productoService.save(productoInstance)
        if (!newProducto?.getId()) {
            render(view: "create", model: [productoInstance: productoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [
                message(code: 'producto.label', default: 'Producto'),
                newProducto.getId()
        ])
        redirect(action: "list")
    }

    @Secured(['ROLE_ADMIN'])
    def update(Integer id) {
        def productoInstance = new ProductoB(params)
        if (id != productoInstance.getId()) {
            flash.message = message(code: 'default.not.equal.message', args: [
                    message(code: 'producto.label', default: 'Producto'),
                    id
            ])
        }

        def newProducto = productoService.update(id, productoInstance)
        if (!newProducto?.getId()) {
            render(view: "create", model: [productoInstance: productoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [
                message(code: 'producto.label', default: 'Producto'),
                newProducto.getId()
        ])
        redirect(action: "list")
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show() { // si no encuentra deberia llevar a la pag de not found 404
        def id = Integer.valueOf(params['id'])
        def productoInstance
        try {
            productoInstance = productoService.getById(id.toInteger())
        } catch (Exception ex) {
            redirect(action: "notFound")
        }
        [productoInstance: productoInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def id = Integer.valueOf(params['id'])
        def productoInstance = productoService.getById(id.toInteger())
        if (!productoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'producto.label', default: 'Producto'),
                    id
            ])
            redirect(action: "list")
            return
        }

        [productoInstance: productoInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def id = Integer.valueOf(params['id'])
        def productoInstance = productoService.getById(id)
        if (!productoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'producto.label', default: 'Producto'),
                    id
            ])
            redirect(action: "list")
            return
        }

        productoService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [
                message(code: 'producto.label', default: 'Producto'),
                id
        ])
        redirect(action: "list")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
