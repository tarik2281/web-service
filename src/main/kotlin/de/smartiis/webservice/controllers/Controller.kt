package de.smartiis.webservice.controllers

import de.smartiis.webservice.entities.User
import de.smartiis.webservice.getLogger
import de.smartiis.webservice.security.UserPrincipal
import de.smartiis.webservice.services.ProductService
import de.smartiis.webservice.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

data class ChangePasswordData(
    val currentPassword: String,
    val newPassword: String
)

fun getCurrentUserPrincipal() = SecurityContextHolder.getContext().authentication.principal as UserPrincipal

@RestController
@RequestMapping("/api", produces = ["application/json"])
class Controller @Autowired constructor(
    private val userService: UserService,
    private val productService: ProductService
) {

  private val logger = getLogger()

  //  @PreAuthorize("isAuthenticated()")
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
    userService.updateContactData(principal.user, data)
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/user/updateShippingAddress")
  fun updateShippingAddress(@RequestBody data: User) {

  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/user/deleteAccount")
  fun deleteAccount(@RequestBody password: String) {

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

  @GetMapping("/download")
  fun downloadFile(request: HttpServletRequest): ResponseEntity<Resource> {
    val resource = UrlResource("file:///D:/freedom/Malware Labor.ova")
    var contentType = request.servletContext.getMimeType(resource.file.absolutePath)
    if (contentType == null)
      contentType = "application/octet-stream"
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource)
  }
}
