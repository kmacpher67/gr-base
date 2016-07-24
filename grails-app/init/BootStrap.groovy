import bootstrap.Person
import com.spontorg.Role
import com.spontorg.ScheduledShout
import com.spontorg.StatusOut
import com.spontorg.User
import com.spontorg.UserRole
import com.spontorg.ExternalApp
import com.spontorg.FacebookUser;

class BootStrap {

    def init = { servletContext ->
		System.out.println(" BOOTSRAP INIT RUNNING")

		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		def facebookRole = Role.findByAuthority('ROLE_FACEBOOK') ?: new Role(authority: 'ROLE_FACEBOOK').save(failOnError: true, flush: true)
		
        // new Person(firstName: 'John', lastName: 'Doe', dateOfBirth: new Date(), email: 'john.doe@company.com', age: 25).save(flush: true);
		
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

		//def testUser = new User('me', 'password').save()
	
		String testDate = "Aug 22 16:02:43 PST 2030"
		def expireAt = new Date().parse("MMM dd H:m:s z yyyy", testDate)		
		
		System.out.println(" BOOTSRAP INIT FacebookUser.findByUid(9999) ")
		
		def fbAdmin = FacebookUser.findByUid(9999) ?: new FacebookUser( uid: 9999, accessToken: "9999", accessTokenExpires: expireAt, user: adminUser).save(flush:true)

		System.out.println(" BOOTSRAP INIT FacebookUser.findByUid(9999) " + fbAdmin.uid)

		def fbGuest = FacebookUser.findByUid(0000) ?: new FacebookUser( uid: 0000, accessToken: "0000", accessTokenExpires: expireAt, user: basicUser).save(flush:true)
		System.out.println(" BOOTSRAP INIT FacebookUser.findByUid(1111) " + fbGuest.uid)

		//def defaultStatus = StatusOut.findByName('default')  ?:
		def externalApp1 = ExternalApp.findByName('default') ?: new ExternalApp(name:'default',description:'default ext app',accessKey:'asdf').save(flush:true)
		// defaultShout
		//def defaultShout = new ScheduledShout("name":ok.defaultValue.tweetOutputText,"eventTitle":"default none available","statusOut":ok.defaultValue)
		//def defaultShout = ScheduledShout.findByName(ScheduledShout.DEFAULTNAME) ?: ScheduledShout("name":ScheduledShout.DEFAULTNAME,"eventTitle":"default none available","statusOut":externalApp1.defaultValue).save(flush:true)
		System.out.println("created defualt ext app& shout"+ externalApp1);
	}
    def destroy = {
	
			System.out.println(" BOOTSRAP DESTROY RUNNING")

    }
}