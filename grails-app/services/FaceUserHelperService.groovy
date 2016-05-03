package com.spontorg

import com.the6hours.grails.springsecurity.facebook.FacebookAuthToken
import com.spontorg.FacebookUser
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import com.spontorg.User
import com.spontorg.UserRole
import com.spontorg.Role
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.impl.FacebookTemplate

/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 15.01.12
 */
class FaceUserHelperService {

	def springSecurityService

	def listOfAdminEmails = ["kmacpher67@yahoo.com", 'ken@kenmapherson.com', 'jwalker1689@gmail.com', 'jwalker1689@yahoo.com', 'cindyamichael@yahoo.com', 'kryssana@outlook.com', 'zzz']

    /**
     *  find user
     * @param uid facebook user id
     */
    FacebookUser findUser(Long uid) {
        log.info("Search for facebook user with id $uid")
        return FacebookUser.findByUid(uid)
    }
	
	FacebookUser getFB(){
		System.out.println("FaceUserHelperService getFB() springSecurityService" + springSecurityService )
		def user = null;
		def facebookUser = null; 
		try{
			user = springSecurityService.currentUser
			facebookUser = FacebookUser.findByUser(user)	
			}
			catch(Exception e){
				System.out.println(" shitter= " + e)
			}	
		return facebookUser
	}

	boolean updateAdminUsers(User user){

		System.out.println("updateAdminUsers user=" + user.name )
	
		def useremail = user.email
		def isHere = listOfAdminEmails.find { it == useremail }
		def adminRole = Role.findByAuthority('ROLE_ADMIN')
		def isAdmin = user.authorities.contains(adminRole)
		System.out.println("updateAdminUsers isHere=" + isHere )
		
			if(isHere!=null && isHere!="" && !isAdmin){
				System.out.println(" ADDING TO ADMIN " + user.name )
				//UserRole.create(user, adminRole)
				def entity = UserRole.findOrSaveByUserAndRole( user,  adminRole)
				System.out.println(" SAVED!!! "+ entity )
				def newroles = UserRole.findByUser(user)
				System.out.println(" size" + newroles )
			}
			else{
			System.out.println("kmacpher NOT THE USER=" + useremail)
			}
	
	}
	
	
	boolean updateFBUserRole(FacebookUser fbUser, Role role){
	
				println(" role authority =" + role.authority)
				println(" fb uid=" + fbUser.uid)
				def thisFBUser = FacebookUser.findByUid(fbUser.uid) 
				println(" found user for update!=" + fbUser.uid)

				
	}
	
	
    /**
     * !!! remove X_ to use this method
     *
     * Called when we have a new facebook user, called on first login to create main app User domain (when
     * we store Facebook User details in own domain)
     *
     * @param token facebook authentication token
     */
    User X_createAppUser(FacebookUser user, FacebookAuthToken token) {
        log.info("Create app user for facebook user $token.uid")
        User person = new User(
                username: "test_$token.uid",
                password: '********',
                enabled: true,
                accountExpired:  false,
                accountLocked: false,
                passwordExpired: false
        )
        person.save(failOnError: true)
        return person
    }

    /**
     * !!! remove X_ to use this method
     *
     * Called when we have a new facebook user, called on first login to create roles list for new user
     *
     * @param user facebook user
     */
    void X_createRoles(FacebookUser user) {
        log.info("Create role for facebook user $user.uid")
        UserRole.create(user.user, Role.findByAuthority('ROLE_USER'))
        UserRole.create(user.user, Role.findByAuthority('ROLE_FACEBOOK'))
		///kmacpher67@yahoo.com
		log.info("X_createRoles $user.user.email ")
		def email=user.user.email
		if(email.equals("kmacpher67@yahoo.com")){
			UserRole.create(user.user, Role.findByAuthority('ROLE_ADMIN'))
		}
		
    }

    /**
     * !!! remove X_ to use this method
     *
     * Must returns object to store in security context for specified facebook user (can return itself)
     *
     * @param user facebook user
     */
    def X_getPrincipal(FacebookUser user) {
        log.info("Ger principal for facebook user #$user.id")
        return user.user
    }

    /**
     * !!! remove X_ to use this method
     *
     * Must return roles list for specified facebook user
     *
     * @param user facebook user
     */
    Collection<GrantedAuthority> X_getRoles(FacebookUser user) {
        log.info("Ger roles for facebook user #$user.id")
        return user.user.authorities.collect {
            new SimpleGrantedAuthority(it.authority)
        }
    }

    /**
     * !!! remove X_ to use this method
     *
     * Must return roles list for specified facebook user
     *
     * @param user facebook user
     */
    void X_prepopulateAppUser(User user, FacebookAuthToken token) {
        log.info("Prepopulate app user")
        user.password = '*******'
        user.username = "test_$token.uid"
        user.accountExpired = false
        user.accountLocked = false
        user.enabled = true
        user.passwordExpired = false
    }

}
