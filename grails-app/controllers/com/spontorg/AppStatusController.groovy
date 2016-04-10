package com.spontorg

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
@Transactional(readOnly = true)
class AppStatusController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AppStatus.list(params), model:[appStatusCount: AppStatus.count()]
    }

    def show(AppStatus appStatus) {
        respond appStatus
    }

    def create() {
        respond new AppStatus(params)
    }

    @Transactional
    def save(AppStatus appStatus) {
        if (appStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (appStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond appStatus.errors, view:'create'
            return
        }

        appStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'appStatus.label', default: 'AppStatus'), appStatus.id])
                redirect appStatus
            }
            '*' { respond appStatus, [status: CREATED] }
        }
    }

    def edit(AppStatus appStatus) {
        respond appStatus
    }

    @Transactional
    def update(AppStatus appStatus) {
        if (appStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (appStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond appStatus.errors, view:'edit'
            return
        }

        appStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'appStatus.label', default: 'AppStatus'), appStatus.id])
                redirect appStatus
            }
            '*'{ respond appStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(AppStatus appStatus) {

        if (appStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        appStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'appStatus.label', default: 'AppStatus'), appStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'appStatus.label', default: 'AppStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
