package de.smartiis.webservice.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig @Autowired constructor(
    private val userDetailsService: AppUserDetailsService,
    private val restAuthenticationEntryPoint: RestAuthenticationEntryPoint,
    private val objectMapper: ObjectMapper
) : WebSecurityConfigurerAdapter() {

  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  override fun configure(http: HttpSecurity) {
    http
        .csrf().disable()
        .exceptionHandling {
          it.authenticationEntryPoint(restAuthenticationEntryPoint)
        }
        .formLogin {
          it.loginProcessingUrl("/api/login")
          it.successHandler { _, response, authentication ->
            response.contentType = "application/json"
            objectMapper.writeValue(response.writer, (authentication.principal as UserPrincipal).user)
          }
          it.failureHandler { _, response, _ ->
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.reasonPhrase)
          }
        }
        .logout {
          it.logoutUrl("/api/logout")
          it.logoutSuccessHandler { _, _, _ -> }
          it.invalidateHttpSession(true)
          it.deleteCookies("JSESSIONID")
        }
  }

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
  }
}
