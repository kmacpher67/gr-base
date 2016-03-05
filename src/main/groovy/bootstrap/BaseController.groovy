package bootstrap

import grails.artefact.Artefact
import grails.transaction.Transactional
import grails.util.GrailsNameUtils
import grails.web.http.HttpHeaders
import org.springframework.http.HttpStatus
import static org.springframework.http.HttpStatus.*

@Artefact('Controller')
@Transactional(readOnly = true)
abstract class BaseController<T> {
    static allowedMethods = [save: "POST", update: "PUT", patch: "PATCH", delete: "DELETE"]

    Class<T> resource
    String resourceName
    String resourceClassName
    boolean readOnly

    BaseController(Class<T> resource) {
        this(resource, false)
    }

    BaseController(Class<T> resource, boolean readOnly) {
        this.resource = resource
        this.readOnly = readOnly
        resourceClassName = resource.simpleName
        resourceName = GrailsNameUtils.getPropertyName(resource)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond listAllResources(params), model: [("${resourceName}Count".toString()): countResources()]
    }

    def show() {
        respond queryForResource(params.id)
    }

    def create() {
        if(handleReadOnly()) {
            return
        }
        respond createResource()
    }

    @Transactional
    def save() {
        println resourceName
        if(handleReadOnly()) {
            return
        }
        def instance = createResource()

        instance.validate()
        if (instance.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond instance.errors, view:'create'
            return
        }

        saveResource instance

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: "${resourceName}.label".toString(), default: resourceClassName), getAttributeWhenSavedOrDeleted(instance)])
                redirect instance
            }
            '*' {
                response.addHeader(HttpHeaders.LOCATION,
                        grailsLinkGenerator.link( resource: this.controllerName, action: 'show',id: instance.id, absolute: true,
                                namespace: hasProperty('namespace') ? this.namespace : null ))
                respond instance, [status: CREATED]
            }
        }
    }

    def edit() {
        if(handleReadOnly()) {
            return
        }
        respond queryForResource(params.id)
    }

    @Transactional
    def patch() {
        update()
    }

    @Transactional
    def update() {
        if(handleReadOnly()) {
            return
        }

        T instance = queryForResource(params.id)
        if (instance == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        instance.properties = getObjectToBind()

        if (instance.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond instance.errors, view:'edit' // STATUS CODE 422
            return
        }

        updateResource instance

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: "${resourceName}.label".toString(), default: resourceClassName), getAttributeWhenSavedOrDeleted(instance)])
                redirect instance
            }
            '*'{
                response.addHeader(HttpHeaders.LOCATION,
                        grailsLinkGenerator.link( resource: this.controllerName, action: 'show',id: instance.id, absolute: true,
                                namespace: hasProperty('namespace') ? this.namespace : null ))
                respond instance, [status: OK]
            }
        }
    }

    @Transactional
    def delete() {
        if(handleReadOnly()) {
            return
        }

        def instance = queryForResource(params.id)
        if (instance == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        deleteResource instance

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: "${resourceName}.label".toString(), default: resourceClassName), getAttributeWhenSavedOrDeleted(instance)])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT } // NO CONTENT STATUS CODE
        }
    }

    protected List<T> listAllResources(Map params) {
        resource.list(params)
    }

    protected Integer countResources() {
        resource.count()
    }

    protected T queryForResource(Serializable id) {
        resource.get(id)
    }

    protected boolean handleReadOnly() {
        if(readOnly) {
            render status: HttpStatus.METHOD_NOT_ALLOWED.value()
            return true
        } else {
            return false
        }
    }

    protected T createResource(Map params) {
        resource.newInstance(params)
    }

    protected T createResource() {
        T instance = resource.newInstance()
        bindData instance, getObjectToBind()
        instance
    }

    protected getObjectToBind() {
        request
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: "${resourceName}.label", default: resourceClassName), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    protected T saveResource(T resource) {
        resource.save flush: true
    }

    protected T updateResource(T resource) {
        saveResource resource
    }

    protected def deleteResource(T resource) {
        resource.delete flush:true
    }

    protected def getAttributeWhenSavedOrDeleted(T resource) {
        resource.id
    }
}
