package com.spontorg

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
@Transactional(readOnly = true)
class TestController {

	static Boolean linkMe = false

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Test.list(params), model:[testCount: Test.count()]
    }

    def show(Test test) {
        respond test
    }

    def create() {
        respond new Test(params)
    }

    @Transactional
    def save(Test test) {
        if (test == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (test.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond test.errors, view:'create'
            return
        }

        test.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'test.label', default: 'Test'), test.id])
                redirect test
            }
            '*' { respond test, [status: CREATED] }
        }
    }

    def edit(Test test) {
        respond test
    }

    @Transactional
    def update(Test test) {
        if (test == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (test.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond test.errors, view:'edit'
            return
        }

        test.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'test.label', default: 'Test'), test.id])
                redirect test
            }
            '*'{ respond test, [status: OK] }
        }
    }

    @Transactional
    def delete(Test test) {

        if (test == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        test.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'test.label', default: 'Test'), test.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'test.label', default: 'Test'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
