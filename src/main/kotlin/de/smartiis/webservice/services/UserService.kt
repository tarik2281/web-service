package de.smartiis.webservice.services

import de.smartiis.webservice.SecureIdGenerator
import de.smartiis.webservice.entities.User
import de.smartiis.webservice.exceptions.EmailAlreadyInUseException
import de.smartiis.webservice.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

  fun getAll(): List<User> = userRepository.findAll()

  fun register(user: User) {
    if (userRepository.existsByEmailAddress(user.emailAddress))
      throw EmailAlreadyInUseException()

    var id: Int
    do {
      id = SecureIdGenerator.next()
    } while (userRepository.existsById(id))

    // TODO: validation
    user.id = id
    user.password = passwordEncoder.encode(user.password)

    userRepository.save(user)
  }
}
