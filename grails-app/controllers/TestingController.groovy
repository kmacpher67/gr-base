import com.spontorg.FacebookUser
import com.spontorg.User

class TestingController {

    def springSecurityService

    def expireToken() {
        User user = springSecurityService.currentUser
        FacebookUser facebookUser = FacebookUser.findByUser(user)
        facebookUser.accessTokenExpires = new Date()
        facebookUser.save()

        render view: '/message', model: [
                title: 'Expire Token',
                message: "Access Token for user $user is forced to expire"
        ]
    }
}
