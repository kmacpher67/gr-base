package com.spontorg

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
@Transactional(readOnly = true)
class StatusOutController {

	// need this to show up on menu
		static Boolean linkMe = true

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond StatusOut.list(params), model:[statusOutCount: StatusOut.count()]
    }

    def show(StatusOut statusOut) {
        respond statusOut
    }

    def create() {
        respond new StatusOut(params)
    }

    @Transactional
    def save(StatusOut statusOut) {
        if (statusOut == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (statusOut.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond statusOut.errors, view:'create'
            return
        }

        statusOut.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'statusOut.label', default: 'StatusOut'), statusOut.id])
                redirect statusOut
            }
            '*' { respond statusOut, [status: CREATED] }
        }
    }

    def edit(StatusOut statusOut) {
        respond statusOut
    }

    @Transactional
    def update(StatusOut statusOut) {
        if (statusOut == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (statusOut.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond statusOut.errors, view:'edit'
            return
        }

        statusOut.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'statusOut.label', default: 'StatusOut'), statusOut.id])
                redirect statusOut
            }
            '*'{ respond statusOut, [status: OK] }
        }
    }

    @Transactional
    def delete(StatusOut statusOut) {

        if (statusOut == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        statusOut.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'statusOut.label', default: 'StatusOut'), statusOut.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'statusOut.label', default: 'StatusOut'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
