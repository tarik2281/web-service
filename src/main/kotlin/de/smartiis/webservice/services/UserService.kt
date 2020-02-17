package de.smartiis.webservice.services

import de.smartiis.webservice.RegisterUserData
import de.smartiis.webservice.SecureIdGenerator
import de.smartiis.webservice.entities.User
import de.smartiis.webservice.exceptions.EmailAlreadyInUseException
import de.smartiis.webservice.exceptions.WrongPasswordException
import de.smartiis.webservice.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

  fun getAll(): Iterable<User> = userRepository.findAll()

  fun register(user: RegisterUserData) {
    if (userRepository.existsByEmailAddress(user.emailAddress))
      throw EmailAlreadyInUseException()

    var id: Int
    do {
      id = SecureIdGenerator.next()
    } while (userRepository.existsById(id))

    val result = User(id, user.emailAddress,
        passwordEncoder.encode(user.newPassword),
        user.sex, user.firstName, user.lastName, user.birthday)

    userRepository.save(result)
  }

  fun update(currentUser: User, updateData: User) {
    if (currentUser.emailAddress != updateData.emailAddress &&
        userRepository.existsByEmailAddress(updateData.emailAddress))
      throw EmailAlreadyInUseException()

    updateData.id = currentUser.id
    updateData.password = currentUser.password

    userRepository.save(updateData)
  }

  fun deleteUser(currentUser: User, currentPassword: String) {
    if (passwordEncoder.matches(currentPassword, currentUser.password)) {
      userRepository.delete(currentUser)
    } else {
      throw WrongPasswordException()
    }
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
