package de.smartiis.webservice

import de.smartiis.webservice.entities.User
import de.smartiis.webservice.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer @Autowired constructor(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) : ApplicationRunner {

  override fun run(args: ApplicationArguments?) {
    userRepository.save(User(emailAddress = "hallo@hallo.de", password = encoder.encode("hallo")))
  }
}
