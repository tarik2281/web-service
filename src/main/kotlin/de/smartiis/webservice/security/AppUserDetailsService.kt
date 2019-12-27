package de.smartiis.webservice.security

import de.smartiis.webservice.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AppUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository.findByUserName(username)

    if (!user.isPresent)
      throw UsernameNotFoundException(username)

    return UserPrincipal(user.get())
  }
}
