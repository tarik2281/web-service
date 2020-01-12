package de.smartiis.webservice

import de.smartiis.webservice.entities.User
import de.smartiis.webservice.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.sql.Date
import java.time.LocalDate

@Component
class DatabaseInitializer @Autowired constructor(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) : ApplicationRunner {

  override fun run(args: ApplicationArguments?) {
    userRepository.save(User(
        id = SecureIdGenerator.next(),
        sex = "male",
        emailAddress = "a",
        password = encoder.encode("a"),
        firstName = "testFirstName",
        lastName = "testLastName",
        birthday = Date.valueOf(LocalDate.of(1990, 1, 1)),
        address = "testAddress",
        city = "testCity",
        country = "testCountry",
        newsletter = true,
        phoneNumber = "testPhoneNumber",
        postalCode = "testPostalCode"))
  }
}
