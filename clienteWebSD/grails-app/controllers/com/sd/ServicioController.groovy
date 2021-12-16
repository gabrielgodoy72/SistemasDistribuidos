package com.sd

import com.sd.beans.servicio.ServicioB
import com.sd.service.servicio.IServicioService
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.beans.factory.annotation.Autowired

import static org.springframework.http.HttpStatus.*

class ServicioController {

    //services
    IServicioService servicioService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def list(Integer id) {
        def params = params.toString()
        def page = Math.max(id?:0,0)
        def servicios = servicioService.getAll(page)
        [servicioInstanceList: servicios.getList(), servicioIntanceTotal: servicios.getTotal(),
         totalPages: servicios.getTotalPages(), currentPage: servicios.getPage(),
         hasNexPage: servicios.getHasNext(), hasPrevPage: servicios.getHasPrev(),
        nextPage: servicios.getNextPage(), prevPage: servicios.getPrevPage()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        [servicioInstance: new ServicioB(params)]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def servicioInstance = new ServicioB(params)
        def newServicio = servicioService.save(servicioInstance)
        if (!newServicio?.getId()) {
            render(view: "create", model: [servicioInstance: servicioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [
                message(code: 'servicio.label', default: 'Servicio'),
                newServicio.getId()
        ])
        redirect(action: "list")
    }

    @Secured(['ROLE_ADMIN'])
    def update(Integer id) {
        def servicioInstance = new ServicioB(params)
        if (id != servicioInstance.getId()) {
            flash.message = message(code: 'default.not.equal.message', args: [
                    message(code: 'servicio.label', default: 'Servicio'),
                    id
            ])
        }

        def newServicio = servicioService.update(id, servicioInstance)
        if (!newServicio?.getId()) {
            render(view: "create", model: [servicioInstance: servicioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [
                message(code: 'servicio.label', default: 'Servicio'),
                newServicio.getId()
        ])
        redirect(action: "list")
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show() { // si no encuentra deberia llevar a la pag de not found 404
        def id = Integer.valueOf(params['id'])
        def servicioInstance
        try {
            servicioInstance = servicioService.getById(id.toInteger())
        } catch (Exception ex) {
            redirect(action: "notFound")
        }
        [servicioInstance: servicioInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def id = Integer.valueOf(params['id'])
        def servicioInstance = servicioService.getById(id.toInteger())
        if (!servicioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'servicio.label', default: 'Servicio'),
                    id
            ])
            redirect(action: "list")
            return
        }

        [servicioInstance: servicioInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def id = Integer.valueOf(params['id'])
        def servicioInstance = servicioService.getById(id)
        if (!servicioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'servicio.label', default: 'Servicio'),
                    id
            ])
            redirect(action: "list")
            return
        }

        servicioService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [
                message(code: 'servicio.label', default: 'Servicio'),
                id
        ])
        redirect(action: "list")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'servicio.label', default: 'Servicio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
