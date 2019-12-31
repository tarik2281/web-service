package de.smartiis.webservice.controllers

import de.smartiis.webservice.entities.User
import de.smartiis.webservice.getLogger
import de.smartiis.webservice.security.UserPrincipal
import de.smartiis.webservice.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api", produces = ["application/json"])
class Controller @Autowired constructor(private val userService: UserService) {

  private val logger = getLogger()

  //  @PreAuthorize("isAuthenticated()")
  @GetMapping("/")
  fun get() = ResponseEntity(userService.getAll(), HttpStatus.OK)

  @PostMapping("/register", consumes = ["application/json"])
  fun registerUser(@RequestBody data: User) {
    userService.register(data)
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/user/me")
  fun getCurrentUser(): ResponseEntity<User> {
    val principal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
    return ResponseEntity(principal.user, HttpStatus.OK)
  }
}
