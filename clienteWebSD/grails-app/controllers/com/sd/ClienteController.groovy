package com.sd

import com.sd.beans.cliente.ClienteB
import com.sd.service.cliente.IClienteService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ClienteController {

    //services
    IClienteService clienteService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer id) {
        def page = Math.max(id?:0,0)
        def clientes = clienteService.getAll(page)

        [clienteInstanceList: clientes.getList(), clienteIntanceTotal: clientes.getTotal()]
    }

    def create() {
        [clienteInstance: new ClienteB(params)]
    }

    def save() {
        def clienteInstance = new ClienteB(params)
        def newCliente = clienteService.save(clienteInstance)
        if (!newCliente?.getId()) {
            render(view: "create", model: [clienteInstance: clienteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [
                message(code: 'cliente.label', default: 'Cliente'),
                newCliente.getId()
        ])
        redirect(action: "list")
    }

    def update(Integer id) {
        def clienteInstance = new ClienteB(params)
        if (id != clienteInstance.getId()) {
            flash.message = message(code: 'default.not.equal.message', args: [
                    message(code: 'cliente.label', default: 'Cliente'),
                    id
            ])
        }

        def newCliente = clienteService.update(id, clienteInstance)
        if (!newCliente?.getId()) {
            render(view: "create", model: [clienteInstance: clienteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [
                message(code: 'cliente.label', default: 'Cliente'),
                newCliente.getId()
        ])
        redirect(action: "list")
    }

    def show() { // si no encuentra deberia llevar a la pag de not found 404
        def id = Integer.valueOf(params['id'])
        def clienteInstance
        try {
            clienteInstance = clienteService.getById(id.toInteger())
        } catch (Exception ex) {
            redirect(action: "notFound")
        }
        [clienteInstance: clienteInstance]
    }

    def edit() {
        def id = Integer.valueOf(params['id'])
        def clienteInstance = clienteService.getById(id.toInteger())
        if (!clienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'cliente.label', default: 'Cliente'),
                    id
            ])
            redirect(action: "list")
            return
        }

        [clienteInstance: clienteInstance]
    }

    def delete() {
        def id = Integer.valueOf(params['id'])
        def clienteInstance = clienteService.getById(id)
        if (!clienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'cliente.label', default: 'Cliente'),
                    id
            ])
            redirect(action: "list")
            return
        }

        clienteService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [
                message(code: 'cliente.label', default: 'Cliente'),
                id
        ])
        redirect(action: "list")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
