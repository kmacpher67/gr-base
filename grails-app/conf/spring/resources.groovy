// Place your Spring DSL code here
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.spontorg.RedirectFailureToRegistration
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

beans = {

    redirectFailureHandlerExample(SimpleUrlAuthenticationFailureHandler) {
        defaultFailureUrl = '/failed'
    }

    redirectFailureHandler(RedirectFailureToRegistration) {
        defaultFailureUrl = '/failed'
        registrationUrl = '/register'
    }

}
