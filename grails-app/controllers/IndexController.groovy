import grails.plugin.springsecurity.SpringSecurityService

class IndexController {

    SpringSecurityService springSecurityService

	
    def index() {
			System.out.println(" INDEX MAIN index.html PAGE ")
			render view: '/index'
	}
	
    def show() {
		System.out.println(" INDEX MAIN SHOW PAGE ")
        def model = [:]
        if (springSecurityService.isLoggedIn()) {
            model.user = springSecurityService.currentUser
        }
        render view: '/index', model: model
    }
}
