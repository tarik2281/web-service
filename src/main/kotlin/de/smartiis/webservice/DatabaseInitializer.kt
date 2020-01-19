package de.smartiis.webservice

import de.smartiis.webservice.entities.Product
import de.smartiis.webservice.entities.User
import de.smartiis.webservice.repositories.ProductRepository
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
    private val productRepository: ProductRepository,
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

    productRepository.save(Product(
        id = SecureIdGenerator.next(),
        title = "Test Product",
        thumbnailUrl = "assets/WerbephotoSmartTub.png",
        shortDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et " +
            "dolore magna aliquyam erat, sed diam voluptua.",
        longDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor" +
            " invidunt ut labore etdolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo" +
            " duo dolores et ea rebum. Stet clitakasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit" +
            " amet. Lorem ipsum dolor sit amet, consetetursadipscing elitr, sed diam nonumy eirmod tempor invidunt" +
            " ut labore et dolore magna aliquyam erat, sed diamvoluptua. At vero eos et accusam et justo duo dolores" +
            " et ea rebum. Stet clita kasd gubergren, no sea takimatasanctus est Lorem ipsum dolor sit amet. Lorem" +
            " ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumyeirmod tempor invidunt ut labore et" +
            " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justoduo dolores et ea rebum." +
            " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.Duis autem vel eum" +
            " iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eufeugiat nulla" +
            " facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzrildelenit" +
            " augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, ",
        price = 4.99
    ))

    productRepository.save(Product(
        id = SecureIdGenerator.next(),
        title = "Test Product",
        thumbnailUrl = "assets/BathTubScreen.png",
        shortDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et " +
            "dolore magna aliquyam erat, sed diam voluptua.",
        longDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor" +
            " invidunt ut labore etdolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo" +
            " duo dolores et ea rebum. Stet clitakasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit" +
            " amet. Lorem ipsum dolor sit amet, consetetursadipscing elitr, sed diam nonumy eirmod tempor invidunt" +
            " ut labore et dolore magna aliquyam erat, sed diamvoluptua. At vero eos et accusam et justo duo dolores" +
            " et ea rebum. Stet clita kasd gubergren, no sea takimatasanctus est Lorem ipsum dolor sit amet. Lorem" +
            " ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumyeirmod tempor invidunt ut labore et" +
            " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justoduo dolores et ea rebum." +
            " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.Duis autem vel eum" +
            " iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eufeugiat nulla" +
            " facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzrildelenit" +
            " augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, ",
        price = 4.99
    ))

    productRepository.save(Product(
        id = SecureIdGenerator.next(),
        title = "Test Product",
        thumbnailUrl = "assets/BathTubScreen.png",
        shortDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et " +
            "dolore magna aliquyam erat, sed diam voluptua.",
        longDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor" +
            " invidunt ut labore etdolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo" +
            " duo dolores et ea rebum. Stet clitakasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit" +
            " amet. Lorem ipsum dolor sit amet, consetetursadipscing elitr, sed diam nonumy eirmod tempor invidunt" +
            " ut labore et dolore magna aliquyam erat, sed diamvoluptua. At vero eos et accusam et justo duo dolores" +
            " et ea rebum. Stet clita kasd gubergren, no sea takimatasanctus est Lorem ipsum dolor sit amet. Lorem" +
            " ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumyeirmod tempor invidunt ut labore et" +
            " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justoduo dolores et ea rebum." +
            " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.Duis autem vel eum" +
            " iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eufeugiat nulla" +
            " facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzrildelenit" +
            " augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, ",
        price = 4.99
    ))

    productRepository.save(Product(
        id = SecureIdGenerator.next(),
        title = "Test Product",
        thumbnailUrl = "assets/BathTubScreen.png",
        shortDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et " +
            "dolore magna aliquyam erat, sed diam voluptua.",
        longDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor" +
            " invidunt ut labore etdolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo" +
            " duo dolores et ea rebum. Stet clitakasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit" +
            " amet. Lorem ipsum dolor sit amet, consetetursadipscing elitr, sed diam nonumy eirmod tempor invidunt" +
            " ut labore et dolore magna aliquyam erat, sed diamvoluptua. At vero eos et accusam et justo duo dolores" +
            " et ea rebum. Stet clita kasd gubergren, no sea takimatasanctus est Lorem ipsum dolor sit amet. Lorem" +
            " ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumyeirmod tempor invidunt ut labore et" +
            " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justoduo dolores et ea rebum." +
            " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.Duis autem vel eum" +
            " iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eufeugiat nulla" +
            " facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzrildelenit" +
            " augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, ",
        price = 4.99
    ))
  }
}
