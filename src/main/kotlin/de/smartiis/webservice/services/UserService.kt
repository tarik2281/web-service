package de.smartiis.webservice.services

import de.smartiis.webservice.SecureIdGenerator
import de.smartiis.webservice.entities.User
import de.smartiis.webservice.exceptions.EmailAlreadyInUseException
import de.smartiis.webservice.exceptions.WrongPasswordException
import de.smartiis.webservice.getLogger
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

  fun updateContactData(currentUser: User, updateData: User) {
    if (currentUser.emailAddress != updateData.emailAddress &&
        userRepository.existsByEmailAddress(updateData.emailAddress))
      throw EmailAlreadyInUseException()

    if (!updateData.emailAddress.isNullOrBlank()) {
      currentUser.emailAddress = updateData.emailAddress
    }

    if (!updateData.firstName.isNullOrBlank()) {
      currentUser.emailAddress = updateData.emailAddress
    }

    if (!updateData.lastName.isNullOrBlank()) {
      currentUser.emailAddress = updateData.emailAddress
    }

    if (!updateData.sex.isNullOrBlank()) {
      currentUser.emailAddress = updateData.emailAddress
    }

    if (!updateData.phoneNumber.isNullOrBlank()) {
      currentUser.emailAddress = updateData.emailAddress
    }

    userRepository.save(currentUser)
  }

  fun changePassword(currentUser: User, currentPassword: String, newPassword: String) {
    if (passwordEncoder.matches(currentPassword, currentUser.password)) {
      currentUser.password = passwordEncoder.encode(newPassword)
      userRepository.save(currentUser)
    } else {
      throw WrongPasswordException()
    }
  }
}
