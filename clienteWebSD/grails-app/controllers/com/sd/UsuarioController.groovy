package com.sd

import com.sd.beans.usuario.UsuarioB
import com.sd.service.usuario.IUsuarioService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UsuarioController {

    //services
    IUsuarioService usuarioService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer id) {
        def page = Math.max(id?:0,0)
        def usuarios
        try {
            usuarios = usuarioService.getAll(page)
        } catch (Exception ex) {
            usuarios = new ArrayList<UsuarioB>()
        }

        [usuarioInstanceList: usuarios, usuarioIntanceTotal: usuarios.size()]
    }

    def create() {
        [usuarioInstance: new UsuarioB(params)]
    }

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

    def show() { // si no encuentra deberia llevar a la pag de not found 404
        def id = Integer.valueOf(params['id'])
        def usuarioInstance = usuarioService.getById(id.toInteger())
        [usuarioInstance: usuarioInstance]
    }

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
