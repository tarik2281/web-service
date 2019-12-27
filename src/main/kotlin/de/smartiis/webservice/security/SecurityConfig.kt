package de.smartiis.webservice.security

import de.smartiis.webservice.security.AppUserDetailsService
import de.smartiis.webservice.security.RestAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig @Autowired constructor(
    private val userDetailsService: AppUserDetailsService,
    private val restAuthenticationEntryPoint: RestAuthenticationEntryPoint
) : WebSecurityConfigurerAdapter() {

  @Bean
  fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

  override fun configure(http: HttpSecurity) {
    http
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .formLogin()
        .loginProcessingUrl("/api/login")
        .successHandler { _, _, _ -> println("login") }
        .failureHandler { _, response, _ -> response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.reasonPhrase) }
        .and()
        .logout()
        .logoutUrl("/api/logout")
        .logoutSuccessHandler { _, _, _ -> }
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
  }

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
  }
}
