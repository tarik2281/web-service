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

  fun update(currentUser: User, updateData: User) {
    if (currentUser.emailAddress != updateData.emailAddress &&
        userRepository.existsByEmailAddress(updateData.emailAddress))
      throw EmailAlreadyInUseException()

    currentUser.address = updateData.address
    currentUser.birthday = updateData.birthday
    currentUser.city = updateData.city
    currentUser.country = updateData.country
    currentUser.sex = updateData.sex
    currentUser.emailAddress = updateData.emailAddress
    currentUser.firstName = updateData.firstName
    currentUser.lastName = updateData.lastName
    currentUser.newsletter = updateData.newsletter
    currentUser.phoneNumber = updateData.phoneNumber
    currentUser.postalCode = updateData.postalCode

    userRepository.save(currentUser)
  }
}
