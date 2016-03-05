package bootstrap

import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
@Transactional(readOnly = true)
class PersonController extends BaseController<Person> {

    PersonController() {
        super(Person)
    }

    protected String getAttributeWhenSavedOrDeleted(Person resource) {
        "$resource.firstName $resource.lastName"
    }
}
