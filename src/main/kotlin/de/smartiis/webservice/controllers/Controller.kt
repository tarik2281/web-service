package de.smartiis.webservice.controllers

import de.smartiis.webservice.repositories.UserRepository
import de.smartiis.webservice.entities.User
import de.smartiis.webservice.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

data class RegisterData(
    val userName: String,
    val password: String
)

@RestController
@RequestMapping("/api", produces = ["application/json"])
class Controller @Autowired constructor(private val userRepository: UserRepository) {

  private val logger = getLogger()

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/")
  fun get() = ResponseEntity(userRepository.findAll(), HttpStatus.OK)

  @PostMapping("/register", consumes = ["application/json"])
  fun registerUser(@RequestBody data: RegisterData) {
    logger.info(data.toString())

    userRepository.save(User(userName = data.userName))
  }
}
