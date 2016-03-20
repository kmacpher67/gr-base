package com.spontorg

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
@Transactional(readOnly = true)
class ExternalAppController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ExternalApp.list(params), model:[externalAppCount: ExternalApp.count()]
    }

    def show(ExternalApp externalApp) {
        respond externalApp
    }

    def create() {
        respond new ExternalApp(params)
    }

    @Transactional
    def save(ExternalApp externalApp) {
        if (externalApp == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (externalApp.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond externalApp.errors, view:'create'
            return
        }

        externalApp.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'externalApp.label', default: 'ExternalApp'), externalApp.id])
                redirect externalApp
            }
            '*' { respond externalApp, [status: CREATED] }
        }
    }

    def edit(ExternalApp externalApp) {
        respond externalApp
    }

    @Transactional
    def update(ExternalApp externalApp) {
        if (externalApp == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (externalApp.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond externalApp.errors, view:'edit'
            return
        }

        externalApp.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'externalApp.label', default: 'ExternalApp'), externalApp.id])
                redirect externalApp
            }
            '*'{ respond externalApp, [status: OK] }
        }
    }

    @Transactional
    def delete(ExternalApp externalApp) {

        if (externalApp == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        externalApp.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'externalApp.label', default: 'ExternalApp'), externalApp.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'externalApp.label', default: 'ExternalApp'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
