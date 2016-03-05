package bootstrap

import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonController extends BaseController<Person> {

    PersonController() {
        super(Person)
    }

    protected String getAttributeWhenSavedOrDeleted(Person resource) {
        "$resource.firstName $resource.lastName"
    }
}
