package com.sd

import com.sd.beans.usuario.UsuarioB
import com.sd.service.usuario.IUsuarioService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import org.springframework.beans.factory.annotation.Autowired

import static org.springframework.http.HttpStatus.*

class UsuarioController {

    //services
    @Autowired
    IUsuarioService usuarioService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def list(Integer id) {
        def page = Math.max(id?:0,0)
        def usuarios = usuarioService.getAll(page)

        [usuarioInstanceList: usuarios.getList(), usuarioIntanceTotal: usuarios.getTotal()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        [usuarioInstance: new UsuarioB(params)]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def usuarioInstance = new UsuarioB(params)
        def newUsuario = usuarioService.save(usuarioInstance)
        if (!newUsuario?.getId()) {
            render(view: "create", model: [usuarioInstance: usuarioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [
                message(code: 'usuario.label', default: 'Usuario'),
                newUsuario.getId()
        ])
        redirect(action: "list")
    }

    @Secured(['ROLE_ADMIN'])
    def update(Integer id) {
        def usuarioInstance = new UsuarioB(params)
        if (id != usuarioInstance.getId()) {
            flash.message = message(code: 'default.not.equal.message', args: [
                    message(code: 'usuario.label', default: 'Usuario'),
                    id
            ])
        }

        def newUsuario = usuarioService.update(id, usuarioInstance)
        if (!newUsuario?.getId()) {
            render(view: "create", model: [usuarioInstance: usuarioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [
                message(code: 'usuario.label', default: 'Usuario'),
                newUsuario.getId()
        ])
        redirect(action: "list")
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show() { // si no encuentra deberia llevar a la pag de not found 404
        def id = Integer.valueOf(params['id'])
        def usuarioInstance
        try {
            usuarioInstance = usuarioService.getById(id.toInteger())
        } catch (Exception ex) {
            redirect(action: "notFound")
        }
        [usuarioInstance: usuarioInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def id = Integer.valueOf(params['id'])
        def usuarioInstance = usuarioService.getById(id.toInteger())
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'usuario.label', default: 'Usuario'),
                    id
            ])
            redirect(action: "list")
            return
        }

        [usuarioInstance: usuarioInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def id = Integer.valueOf(params['id'])
        def usuarioInstance = usuarioService.getById(id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'usuario.label', default: 'Usuario'),
                    id
            ])
            redirect(action: "list")
            return
        }

        usuarioService.delete(id)

        flash.message = message(code: 'default.deleted.message', args: [
                message(code: 'usuario.label', default: 'Usuario'),
                id
        ])
        redirect(action: "list")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
