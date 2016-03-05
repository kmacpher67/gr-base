import bootstrap.Person

class BootStrap {

    def init = { servletContext ->
        new Person(firstName: 'John', lastName: 'Doe', dateOfBirth: new Date(), email: 'john.doe@company.com', age: 25).save(flush: true);
    }
    def destroy = {
    }
}
