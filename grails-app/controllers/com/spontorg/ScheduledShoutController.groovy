package com.spontorg

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
@Transactional(readOnly = true)
class ScheduledShoutController {

	// need this to show up on menu
		static Boolean linkMe = true

    // removed , delete: "DELETE"
    static allowedMethods = [save: "POST", update: "PUT"]

    def index(Integer max) {
        params.max = Math.min(max ?: 50, 9999)
        respond ScheduledShout.list(params), model:[scheduledShoutCount: ScheduledShout.count()]
    }

    def resetAll(){

        ScheduledShout.executeUpdate("update ScheduledShout set delivered=false; ")
    }

    def show(ScheduledShout scheduledShout) {
        respond scheduledShout
    }

    def create() {
        respond new ScheduledShout(params)
    }

    @Transactional
    def save(ScheduledShout scheduledShout) {
        if (scheduledShout == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (scheduledShout.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond scheduledShout.errors, view:'create'
            return
        }

        scheduledShout.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'scheduledShout.label', default: 'ScheduledShout'), scheduledShout.id])
                redirect scheduledShout
            }
            '*' { respond scheduledShout, [status: CREATED] }
        }
    }

    def edit(ScheduledShout scheduledShout) {
        respond scheduledShout
    }

    @Transactional
    def update(ScheduledShout scheduledShout) {
        if (scheduledShout == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (scheduledShout.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond scheduledShout.errors, view:'edit'
            return
        }

        scheduledShout.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'scheduledShout.label', default: 'ScheduledShout'), scheduledShout.id])
                redirect scheduledShout
            }
            '*'{ respond scheduledShout, [status: OK] }
        }
    }

    @Transactional
    def delete(ScheduledShout scheduledShout) {

        if (scheduledShout == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        scheduledShout.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'scheduledShout.label', default: 'ScheduledShout'), scheduledShout.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'scheduledShout.label', default: 'ScheduledShout'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
