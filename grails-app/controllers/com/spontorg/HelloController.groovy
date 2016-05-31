package com.spontorg

import javax.inject.Inject;


import grails.plugin.springsecurity.SpringSecurityService
import com.the6hours.grails.springsecurity.facebook.*
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class HelloController {

    //SpringSecurityService springSecurityService
	def springSecurityService
	def facebook
	def connectionRepository
	def faceUserHelperService
	def imageGrabberService
	
  	static Boolean linkMe = true

	def captureImage(){
		println(" RUNNING capture mode" )
		def baseFolder = grailsAttributes.getApplicationContext().getResource("/").file.absolutePath
		println("captureImage running! - baseFolder="+baseFolder)
		imageGrabberService.baseFolder=baseFolder
		def imagein =imageGrabberService.captureImage()

		def imageList = [imagein]
		println imageList


		showImages("")
	}

	def showImages(String imgDir){
		if (imgDir==null || imgDir==""){
			imgDir="images"
		}
		println("\n\n SHOW IMAGES imgDir="+ imgDir)
		def model =[:]
		def baseFolder = grailsAttributes.getApplicationContext().getResource("/"+imgDir).file.absolutePath
		//def baseFolder = grailsAttributes.getApplicationContext().getResource("/").getFile().toString()
//		def baseFolder = new File(".").listFiles().toString()
//		println baseFolder
//		def imagesFolder = baseFolder + imgDir
//		println imagesFolder
//		a.lastModified() <=> b.lastModified()
//		a.lastModified() <=> b.lastModified()
//		//to reverse sort on lastModified date use below
//		//-(a.lastModified() <=> b.lastModified())

		def imageList = []
		File[] imageList1 = new File(baseFolder).listFiles()?.sort{a, b ->
			a.lastModified() <=> b.lastModified()
		}
		println imageList1
		imageList1.each { def f ->
							imageList.add(f.name)
						}
		println imageList
		println "size="+ imageList1.size()
		println "size="+ imageList.size()
		model.imageList=imageList
		println(" meta data typeName=" + imageList1.getClass().typeName )

		//imageGrabberService.createAnimatedGif(imageList1)
		imageGrabberService.publishEvent( imageList1)

		render view: 'imageView', model: model
	}

    def helloFB = {
		System.out.println("helloFB wowo")

		def model = [:]
		System.out.println("springSecurityService" + springSecurityService )
		try{
			def facebookUser = getFB()
			def email=facebookUser.user.email
			def adminRole = Role.findByAuthority('ROLE_ADMIN')
			def usercurr = User.findByUsername(facebookUser.user.username)
			System.out.println("helloFB usercurr="+usercurr )
			def omg = faceUserHelperService.updateAdminUsers(facebookUser.user)
			
			model.user = springSecurityService.currentUser
			def facebook = new FacebookTemplate( facebookUser.accessToken )
			System.out.println("\n\n\n " + facebookUser.user.authorities.contains(adminRole) )
			render view: 'index2', model: model
			}
			catch(Exception e){
				System.out.println(" shit" + e)
				render view: 'connect'
			}
		
        // if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            // return "redirect:/connect/facebook";
        // }
		render view: 'connect'
        //return "hello";
    }
	
	FacebookUser getFB(){
		System.out.println("getFB() springSecurityService" + springSecurityService )
		def user = null;
		def facebookUser = null; 
		try{
			user = springSecurityService.currentUser
			facebookUser = FacebookUser.findByUser(user)	
			}
			catch(Exception e){
				System.out.println(" shit" + e)
				render view: 'connect'
			}	
		return facebookUser
	}
	
	def index = {
		System.out.println("INDEX nully="+ springSecurityService.isLoggedIn())
        def model = [:]
        if (springSecurityService.isLoggedIn()) {
            model.user = springSecurityService.currentUser
            System.out.println("INDEX not NULLY="+ model.user)
			    render view: 'index', model: model
        }
        render view: 'index2', model: model
	}
	
	
  def profilePhoto = {
    System.out.println("piggy profile photo")
    response.outputStream << facebook.userOperations().getUserProfileImage()
  }
  
  def feed = {
      System.out.println("piggy feed")
	      
    try{
	
		System.out.println("com.the6hours.grails.springsecurity.facebook.FacebookAccessToken=" + com.the6hours.grails.springsecurity.facebook.FacebookAccessToken )
		// System.out.println("token.accessToken.accessToke=" +token.accessToken.accessToken)
	
	    User user = springSecurityService.currentUser
		      System.out.println("user" + user)
			  
		// def facebook = new FacebookTemplate(token.accessToken.accessToken)
				      // System.out.println("facebook" + facebook)
		def facebookUser = FacebookUser.findByUser(user)
		System.out.println("facebookUser toekn" + facebookUser.accessToken )
		def facebook = new FacebookTemplate( facebookUser.accessToken )
				      System.out.println("facebook" + facebook)
		def fbProfile = facebook.userOperations().userProfile

		System.out.println("fbProfile= " + fbProfile)
		String email = fbProfile.email
		System.out.println(" email = " + email)
		String name = fbProfile.name
		System.out.println("name" + name ) 
		
		def model = ['feed': facebook.feedOperations()?.getFeed()]
		render(view: 'index2' , model: model)
		}
		catch(Exception e){
			System.out.println("ouch my balls = "+e)
			//render(index:  ['crappy':'dodo'])
			render view: 'connect'
		}
	
  }

  def update = {
    def message = params.id ?: params.message
    facebook.feedOperations().updateStatus(message);
    redirect(action: feed)
  }

  def friends = {
    def model = ["friends": facebook.friendOperations().getFriendProfiles()]
    render(view: pluginConfig.page.friends, model: model)
  }

  def albums = {
    def model = ["albums": facebook.mediaOperations().getAlbums()]
    render(view: pluginConfig.page.albums, model: model)
  }

  def album = {
    def albumId = params.id ?: params.albumId
    def model = [:]
    model.album = facebook.mediaOperations().getAlbum(albumId)
    model.photos = facebook.mediaOperations().getPhotos(albumId)

    render(view: pluginConfig.page.album, model: model)
  }

  def login = {
    render(view: pluginConfig.page.connect)
  }

  def auth() {
    if (!isConnected()) {
      redirect(action: 'login')
      return false
    }
  }

  Boolean isConnected() {
    connectionRepository.findPrimaryConnection(Facebook)
  }
  
}
