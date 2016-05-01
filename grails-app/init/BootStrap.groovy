import bootstrap.Person
import com.spontorg.Role
import com.spontorg.User
import com.spontorg.UserRole
import com.spontorg.ExternalApp

class BootStrap {

    def init = { servletContext ->

		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		def facebookRole = Role.findByAuthority('ROLE_FACEBOOK') ?: new Role(authority: 'ROLE_FACEBOOK').save(failOnError: true, flush: true)
		
        new Person(firstName: 'John', lastName: 'Doe', dateOfBirth: new Date(), email: 'john.doe@company.com', age: 25).save(flush: true);

		def testUser = new User('me', 'password').save()

		def adminUser = User.findByUsername('admin') ?: new User(
				username: 'admin',
				password: 'admin',
				enabled: true).save(failOnError: true)

		def basicUser = User.findByUsername('guest') ?: new User(
				username: 'guest',
				password: 'guest',                          //pw encoded by security plugin
				enabled: true).save(failOnError: true)

		if (!adminUser.authorities.contains(adminRole)) {
			UserRole.create adminUser, adminRole
		}
		if (!basicUser.authorities.contains(userRole)) {
			UserRole.create basicUser, userRole
		}
			
		def externalApp1 = ExternalApp.findByName('default') ?: new ExternalApp(name:'default',description:'default ext app',accessKey:'asdf').save(flush:true)
    }
    def destroy = {
    }
}