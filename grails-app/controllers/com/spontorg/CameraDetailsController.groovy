package com.spontorg

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
@Transactional(readOnly = true)
class CameraDetailsController {

    static Boolean linkMe = true

    //, delete: "DELETE"
    static allowedMethods = [save: "POST", update: "PUT"]

    def index(Integer max) {
        params.max = Math.min(max ?: 50, 999)
        respond CameraDetails.list(params), model:[cameraDetailsCount: CameraDetails.count()]
    }

    def show(CameraDetails cameraDetails) {
        respond cameraDetails
    }

    def create() {
        respond new CameraDetails(params)
    }

    @Transactional
    def save(CameraDetails cameraDetails) {
        if (cameraDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (cameraDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cameraDetails.errors, view:'create'
            return
        }

        cameraDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cameraDetails.label', default: 'CameraDetails'), cameraDetails.id])
                redirect cameraDetails
            }
            '*' { respond cameraDetails, [status: CREATED] }
        }
    }

    def edit(CameraDetails cameraDetails) {
        respond cameraDetails
    }

    @Transactional
    def update(CameraDetails cameraDetails) {
        if (cameraDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (cameraDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cameraDetails.errors, view:'edit'
            return
        }

        cameraDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cameraDetails.label', default: 'CameraDetails'), cameraDetails.id])
                redirect cameraDetails
            }
            '*'{ respond cameraDetails, [status: OK] }
        }
    }

    @Transactional
    def delete(CameraDetails cameraDetails) {

        if (cameraDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        cameraDetails.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cameraDetails.label', default: 'CameraDetails'), cameraDetails.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cameraDetails.label', default: 'CameraDetails'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
