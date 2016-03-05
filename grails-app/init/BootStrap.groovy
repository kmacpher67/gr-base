import bootstrap.Person
import com.spontorg.Role
import com.spontorg.User
import com.spontorg.UserRole

class BootStrap {

    def init = { servletContext ->
        new Person(firstName: 'John', lastName: 'Doe', dateOfBirth: new Date(), email: 'john.doe@company.com', age: 25).save(flush: true);
		def r = new Role("ROLE_ADMIN").save();
		def userRole = new Role('ROLE_USER').save();
		
		def u = new User(username: 'admin', password: 'admin').save();	
		def testUser = new User('me', 'password').save()
		
		def ur= UserRole.create(u,r);
		def tr= UserRole.create(testUser,userRole);
    }
    def destroy = {
    }
}
