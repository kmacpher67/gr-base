package com.spontorg

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
@Transactional(readOnly = true)
class StatusTimeLineController {

    // need this to show up on menu
    static Boolean linkMe = true

    // removed delete: "DELETE"
    static allowedMethods = [save: "POST", update: "PUT" ]

    def index(Integer max) {
        params.max = Math.min(max ?: 50, 9999)
        respond StatusTimeLine.list(params), model:[statusTimeLineCount: StatusTimeLine.count()]
    }

    def show(StatusTimeLine statusTimeLine) {
        respond statusTimeLine
    }

    def create() {
        respond new StatusTimeLine(params)
    }

    @Transactional
    def save(StatusTimeLine statusTimeLine) {
        if (statusTimeLine == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (statusTimeLine.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond statusTimeLine.errors, view:'create'
            return
        }

        statusTimeLine.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'statusTimeLine.label', default: 'StatusTimeLine'), statusTimeLine.id])
                redirect statusTimeLine
            }
            '*' { respond statusTimeLine, [status: CREATED] }
        }
    }

    def edit(StatusTimeLine statusTimeLine) {
        respond statusTimeLine
    }

    @Transactional
    def update(StatusTimeLine statusTimeLine) {
        if (statusTimeLine == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (statusTimeLine.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond statusTimeLine.errors, view:'edit'
            return
        }

        statusTimeLine.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'statusTimeLine.label', default: 'StatusTimeLine'), statusTimeLine.id])
                redirect statusTimeLine
            }
            '*'{ respond statusTimeLine, [status: OK] }
        }
    }

    @Transactional
    def delete(StatusTimeLine statusTimeLine) {

        if (statusTimeLine == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        statusTimeLine.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'statusTimeLine.label', default: 'StatusTimeLine'), statusTimeLine.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'statusTimeLine.label', default: 'StatusTimeLine'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
