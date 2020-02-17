package de.smartiis.webservice.security

import de.smartiis.webservice.entities.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class UserPrincipal(var user: User) : UserDetails {
  override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
      Collections.singleton(SimpleGrantedAuthority("USER"))

  override fun isEnabled(): Boolean = true

  override fun getUsername(): String = user.emailAddress

  override fun isCredentialsNonExpired(): Boolean = true

  override fun getPassword(): String = user.password

  override fun isAccountNonExpired(): Boolean = true

  override fun isAccountNonLocked(): Boolean = true
}
