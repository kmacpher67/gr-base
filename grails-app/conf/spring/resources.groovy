// Place your Spring DSL code here
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.spontorg.RedirectFailureToRegistration
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

beans = {

    redirectFailureHandlerExample(SimpleUrlAuthenticationFailureHandler) {
        defaultFailureUrl = '/login/auth'
		//used to be failed 
    }

    redirectFailureHandler(RedirectFailureToRegistration) {
        defaultFailureUrl = '/login/auth'
        registrationUrl = '/register'
    }

}
