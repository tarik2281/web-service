package de.smartiis.webservice.controllers

import de.smartiis.webservice.entities.User
import de.smartiis.webservice.getLogger
import de.smartiis.webservice.security.UserPrincipal
import de.smartiis.webservice.services.ProductService
import de.smartiis.webservice.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

fun getCurrentUserPrincipal() = SecurityContextHolder.getContext().authentication.principal as UserPrincipal

@RestController
@RequestMapping("/api", produces = ["application/json"])
class Controller @Autowired constructor(
    private val userService: UserService,
    private val productService: ProductService
) {

  private val logger = getLogger()

  @GetMapping("/")
  fun get() = ResponseEntity(userService.getAll(), HttpStatus.OK)

  @PostMapping("/register", consumes = ["application/json"])
  fun registerUser(@RequestBody data: User) {
    userService.register(data)
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/user/update")
  fun updateUser(@RequestBody data: User) {
    val principal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
    userService.update(principal.user, data)
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/user/me")
  fun getCurrentUser(): ResponseEntity<User> {
    val principal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
    return ResponseEntity(principal.user, HttpStatus.OK)
  }

  @GetMapping("/products")
  fun getAllProducts() = productService.getAll()

  @GetMapping("/products/{id}")
  fun getProductById(@PathVariable("id") id: Int) = productService.getById(id)
}
