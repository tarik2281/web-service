package de.smartiis.webservice.controllers

import de.smartiis.webservice.entities.OrderC
import de.smartiis.webservice.entities.RegisterUserData
import de.smartiis.webservice.entities.SupportTicket
import de.smartiis.webservice.entities.User
import de.smartiis.webservice.getLogger
import de.smartiis.webservice.security.UserPrincipal
import de.smartiis.webservice.services.OrderService
import de.smartiis.webservice.services.ProductService
import de.smartiis.webservice.services.SupportService
import de.smartiis.webservice.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

data class ChangePasswordData(
    val currentPassword: String,
    val newPassword: String
)

fun getCurrentUserPrincipal() = SecurityContextHolder.getContext().authentication.principal as UserPrincipal

@RestController
@RequestMapping("/api", produces = ["application/json"])
class Controller @Autowired constructor(
    private val userService: UserService,
    private val productService: ProductService,
    private val supportService: SupportService,
    private val orderService: OrderService
) {

  private val logger = getLogger()

  //  @PreAuthorize("isAuthenticated()")
  @GetMapping("/")
  fun get() = ResponseEntity(userService.getAll(), HttpStatus.OK)

  @PostMapping("/order/place", consumes = ["application/json"])
  fun placeOrder(@RequestBody order: OrderC) {
    orderService.placeOrder(order)
  }

  @PostMapping("/order/cancel")
  fun cancelOrder(@RequestBody orderId: Int) {
    orderService.requestCancel(orderId)
  }

  @GetMapping("/order/all")
  fun getAllOrders() = ResponseEntity(orderService.getAll(), HttpStatus.OK)

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/orders")
  fun getOrders(): ResponseEntity<Iterable<OrderC>> {
    val user = getCurrentUserPrincipal().user
    return ResponseEntity(orderService.getAllForUser(user.id), HttpStatus.OK)
  }

  @PostMapping("/register", consumes = ["application/json"])
  fun registerUser(@RequestBody data: RegisterUserData) {
    userService.register(data)
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/user/update")
  fun updateUser(@RequestBody data: User) {
    val principal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
    userService.update(principal.user, data)
    principal.user = data
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/user/deleteAccount")
  fun deleteAccount(@RequestBody password: String) {
    userService.deleteUser(getCurrentUserPrincipal().user, password);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/user/change-password", consumes = ["application/json"])
  fun changePassword(@RequestBody data: ChangePasswordData) {
    userService.changePassword(getCurrentUserPrincipal().user, data.currentPassword, data.newPassword)
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
  fun getProductById(@PathVariable("id") id: String) = productService.getById(id)

  @PostMapping("/support/ticket", consumes = ["application/json"])
  fun addSupportTicket(@RequestBody supportTicket: SupportTicket) {
    supportService.addTicket(supportTicket)
  }

  @PostMapping("/support/registerNewsletter")
  fun registerNewsletter(@RequestBody emailAddress: String) {
    supportService.registerNewsletter(emailAddress)
  }
}
