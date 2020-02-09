package de.smartiis.webservice

import de.smartiis.webservice.entities.Description
import de.smartiis.webservice.entities.Product
import de.smartiis.webservice.entities.ProductVariant
import de.smartiis.webservice.entities.User
import de.smartiis.webservice.repositories.ProductRepository
import de.smartiis.webservice.repositories.ProductVariantRepository
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
    private val productVariantRepository: ProductVariantRepository,
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

//    productRepository.save(Product(
//        id = SecureIdGenerator.next(),
//        title = "Test Product",
//        thumbnailUrl = "assets/WerbephotoSmartTub.png",
//        shortDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et " +
//            "dolore magna aliquyam erat, sed diam voluptua.",
//        longDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor" +
//            " invidunt ut labore etdolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo" +
//            " duo dolores et ea rebum. Stet clitakasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit" +
//            " amet. Lorem ipsum dolor sit amet, consetetursadipscing elitr, sed diam nonumy eirmod tempor invidunt" +
//            " ut labore et dolore magna aliquyam erat, sed diamvoluptua. At vero eos et accusam et justo duo dolores" +
//            " et ea rebum. Stet clita kasd gubergren, no sea takimatasanctus est Lorem ipsum dolor sit amet. Lorem" +
//            " ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumyeirmod tempor invidunt ut labore et" +
//            " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justoduo dolores et ea rebum." +
//            " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.Duis autem vel eum" +
//            " iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eufeugiat nulla" +
//            " facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzrildelenit" +
//            " augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, ",
//        price = 4.99
//    ))

    productRepository.save(Product(
        idString = "smarttub",
        title = "SmartTub",
        overviewDescription = "Die intelligente Badewannenarmatur mit integrierter Badezusatzdosierung und vielen weiteren Features"
    ))

    productVariantRepository.save(ProductVariant(
        id = SecureIdGenerator.next(),
        productId = "smarttub",
        title = "SmartTub",
        cartTitle = "SmartTub",
        price = 49.99,
        images = listOf("assets/WerbephotoSmartTub.png", "assets/Funktionsbeschreibung1.PNG", "assets/Funktionsbeschreibung2 (alt2).PNG", "assets/Funktionsbeschreibung3.PNG"),
        shortDescription = "Die intelligente Badewannenarmatur bietet zahlreiche Features",
        features = listOf("Bedienung per Touch",
            "Fernsteuerung per App",
            "Integrierte wasserdichte Lautsprecher (IP-67)",
            "Bluetooth zur Musikwiedergabe",
            "Nachfüllfunktion für Badezusätze",
            "Automatische Badewannenablauf",
            "Einstellbare Bedeprogramme",
            "Integrierte Uhr"),
        longDescription = mutableListOf(
            Description(
                title = "Duschfunktion",
                content = "Da die SmartTub jedoch physisch an die Badewanne installiert werden muss und somit die gesamte\n" +
                    "Armatur ersetzt, ist ebenfalls eine Duschfunktion vorhanden, sowie die Möglichkeit einfach kaltes\n" +
                    "Wasser aus Duschkopf oder Badezulauf laufen zu lassen"
            ),
            Description(
                title = "Funktionale Armatur",
                content = "Die SmartTub besteht aus einer Armatur, in welcher mehrere Funktionen vereint sind.\n" +
                    "Oben links ist die Temperaturanzeige der auszugebenen\n" +
                    "Wassertemperatur, welche mit den Pfeilen nach oben und\n" +
                    "nach unten in halben Grad Celsius-Schritten verändert\n" +
                    "werden kann."
            ),
            Description(
                content = "Die Temperatur wird immer dann angezeigt, wenn ein\n" +
                    "Nutzer den Wasserzufluss über den Duschkopf oder den\n" +
                    "Badewanneneinlass anschaltet. Läuft gerade kein Wasser, so\n" +
                    "wird an der Stelle die aktuelle Uhrzeit angezeigt. Die Funktionen\n" +
                    "zum Einstellen der Wassertemperatur, sowie zum Start des\n" +
                    "Wasserzulaufs in die Badewanne, können ebenfalls über\n" +
                    "die Smart-Home-App gesteuert werden."
            ),
            Description(
                title = "Lautsprecher",
                content = "Zu jeder Seite des SmartTub sind zusätzlich wasserdichte Lautsprecher installiert, sodass der Nutzer\n" +
                    "bei Bedarf Musik während des Badens hören kann. Die Musikauswahl erfolgt dabei über das\n" +
                    "Smartphone. Darüber hinaus können die Lautsprecher auch einfach per Bluetooth mit jedem anderen\n" +
                    "Ausgabegerät gekoppelt werden."
            )
        )
    ))

    productRepository.save(Product(
        idString = "smartscent",
        title = "SmarTscent",
        overviewDescription = "Der Badezusatz passend zu deinem SmartTub in verschiedenen Sorten"
    ))

    productVariantRepository.save(ProductVariant(
        id = SecureIdGenerator.next(),
        productId = "smartscent",
        title = "Cherry",
        cartTitle = "SmarTscent Cherry",
        price = 4.99,
        images = listOf("assets/cherry.png"),
        shortDescription = "Die wohl duftenden Badezusätze für deine Erholung sind in vielen verschiedenen Sorten\n" +
            "erhältlich. Dermatologen bestätigen die besondere Hautverträglichkeit.",
        features = listOf("besonders Hautverträglich",
            "pH-Hautneutral",
            "Schaumbad",
            "Eintauchen und entspannen")
    ))

    productVariantRepository.save(ProductVariant(
        id = SecureIdGenerator.next(),
        productId = "smartscent",
        title = "Lemon",
        cartTitle = "SmarTscent Lemon",
        price = 4.99,
        images = listOf("assets/lemon.png"),
        shortDescription = "Die wohl duftenden Badezusätze für deine Erholung sind in vielen verschiedenen Sorten\n" +
            "erhältlich. Dermatologen bestätigen die besondere Hautverträglichkeit.",
        features = listOf("besonders Hautverträglich",
            "pH-Hautneutral",
            "Schaumbad",
            "Eintauchen und entspannen")
    ))

    productVariantRepository.save(ProductVariant(
        id = SecureIdGenerator.next(),
        productId = "smartscent",
        title = "Rose",
        cartTitle = "SmarTscent Rose",
        price = 4.99,
        images = listOf("assets/rose.png"),
        shortDescription = "Die wohl duftenden Badezusätze für deine Erholung sind in vielen verschiedenen Sorten\n" +
            "erhältlich. Dermatologen bestätigen die besondere Hautverträglichkeit.",
        features = listOf("besonders Hautverträglich",
            "pH-Hautneutral",
            "Schaumbad",
            "Eintauchen und entspannen")
    ))

    productVariantRepository.save(ProductVariant(
        id = SecureIdGenerator.next(),
        productId = "smartscent",
        title = "Green Tea",
        cartTitle = "SmarTscent Green Tea",
        price = 4.99,
        images = listOf("assets/green tee.png"),
        shortDescription = "Die wohl duftenden Badezusätze für deine Erholung sind in vielen verschiedenen Sorten\n" +
            "erhältlich. Dermatologen bestätigen die besondere Hautverträglichkeit.",
        features = listOf("besonders Hautverträglich",
            "pH-Hautneutral",
            "Schaumbad",
            "Eintauchen und entspannen")
    ))


//    productRepository.save(Product(
//        idString = "smarttub",
//        title = "SmartTub",
//        price = 49.99,
//        images = "assets/WerbephotoSmartTub.png|assets/Funktionsbeschreibung1.PNG|assets/Funktionsbeschreibung2 (alt2).PNG|assets/Funktionsbeschreibung3.PNG",
//        shortDescription = "Die intelligente Badewannenarmatur bietet zahlreiche Features",
//        features = "Bedienung per Touch|" +
//            "Fernsteuerung per App|" +
//            "Integrierte wasserdichte Lautsprecher (IP-67)|" +
//            "Bluetooth zur Musikwiedergabe|" +
//            "Nachfüllfunktion für Badezusätze|" +
//            "Automatische Badewannenablauf|" +
//            "Einstellbare Bedeprogramme|" +
//            "Integrierte Uhr",
//        overviewDescription = "Die intelligente Badewannenarmatur mit integrierter Badezusatzdosierung und vielen weiteren Features",
//        longDescription = "[{\"title\":\"Duschfunktion\",\"content\":\"Da die SmartTub jedoch physisch an die Badewanne installiert werden muss und somit die gesamte\\nArmatur ersetzt, ist ebenfalls eine Duschfunktion vorhanden, sowie die Möglichkeit einfach kaltes\\nWasser aus Duschkopf oder Badezulauf laufen zu lassen\"},{\"title\":\"Funktionale Armatur\",\"content\":\"Die SmartTub besteht aus einer Armatur, in welcher mehrere Funktionen vereint sind.\\nOben links ist die Temperaturanzeige der auszugebenen\\nWassertemperatur, welche mit den Pfeilen nach oben und\\nnach unten in halben Grad Celsius-Schritten verändert\\nwerden kann.\"},{\"content\":\"Die Temperatur wird immer dann angezeigt, wenn ein\\nNutzer den Wasserzufluss über den Duschkopf oder den\\nBadewanneneinlass anschaltet. Läuft gerade kein Wasser, so\\nwird an der Stelle die aktuelle Uhrzeit angezeigt. Die Funktionen\\nzum Einstellen der Wassertemperatur, sowie zum Start des\\nWasserzulaufs in die Badewanne, können ebenfalls über\\ndie Smart-Home-App gesteuert werden.\"},{\"title\":\"Lautsprecher\",\"content\":\"Zu jeder Seite des SmartTub sind zusätzlich wasserdichte Lautsprecher installiert, sodass der Nutzer\\nbei Bedarf Musik während des Badens hören kann. Die Musikauswahl erfolgt dabei über das\\nSmartphone. Darüber hinaus können die Lautsprecher auch einfach per Bluetooth mit jedem anderen\\nAusgabegerät gekoppelt werden.\"}]"
//    ))
//
//    productRepository.save(Product(
//        idString = "smartscent",
//        title = "SmarTscent",
//        price = 4.99,
//        images = "",
//
//    ))
//
//    productRepository.save(Product(
//        id = SecureIdGenerator.next(),
//        title = "Test Product",
//        thumbnailUrl = "assets/BathTubScreen.png",
//        shortDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et " +
//            "dolore magna aliquyam erat, sed diam voluptua.",
//        longDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor" +
//            " invidunt ut labore etdolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo" +
//            " duo dolores et ea rebum. Stet clitakasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit" +
//            " amet. Lorem ipsum dolor sit amet, consetetursadipscing elitr, sed diam nonumy eirmod tempor invidunt" +
//            " ut labore et dolore magna aliquyam erat, sed diamvoluptua. At vero eos et accusam et justo duo dolores" +
//            " et ea rebum. Stet clita kasd gubergren, no sea takimatasanctus est Lorem ipsum dolor sit amet. Lorem" +
//            " ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumyeirmod tempor invidunt ut labore et" +
//            " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justoduo dolores et ea rebum." +
//            " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.Duis autem vel eum" +
//            " iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eufeugiat nulla" +
//            " facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzrildelenit" +
//            " augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, ",
//        price = 4.99
//    ))
//
//    productRepository.save(Product(
//        id = SecureIdGenerator.next(),
//        title = "Test Product",
//        thumbnailUrl = "assets/BathTubScreen.png",
//        shortDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et " +
//            "dolore magna aliquyam erat, sed diam voluptua.",
//        longDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor" +
//            " invidunt ut labore etdolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo" +
//            " duo dolores et ea rebum. Stet clitakasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit" +
//            " amet. Lorem ipsum dolor sit amet, consetetursadipscing elitr, sed diam nonumy eirmod tempor invidunt" +
//            " ut labore et dolore magna aliquyam erat, sed diamvoluptua. At vero eos et accusam et justo duo dolores" +
//            " et ea rebum. Stet clita kasd gubergren, no sea takimatasanctus est Lorem ipsum dolor sit amet. Lorem" +
//            " ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumyeirmod tempor invidunt ut labore et" +
//            " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justoduo dolores et ea rebum." +
//            " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.Duis autem vel eum" +
//            " iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eufeugiat nulla" +
//            " facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzrildelenit" +
//            " augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, ",
//        price = 4.99
//    ))
//
//    productRepository.save(Product(
//        id = SecureIdGenerator.next(),
//        title = "Test Product",
//        thumbnailUrl = "assets/BathTubScreen.png",
//        shortDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et " +
//            "dolore magna aliquyam erat, sed diam voluptua.",
//        longDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor" +
//            " invidunt ut labore etdolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo" +
//            " duo dolores et ea rebum. Stet clitakasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit" +
//            " amet. Lorem ipsum dolor sit amet, consetetursadipscing elitr, sed diam nonumy eirmod tempor invidunt" +
//            " ut labore et dolore magna aliquyam erat, sed diamvoluptua. At vero eos et accusam et justo duo dolores" +
//            " et ea rebum. Stet clita kasd gubergren, no sea takimatasanctus est Lorem ipsum dolor sit amet. Lorem" +
//            " ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumyeirmod tempor invidunt ut labore et" +
//            " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justoduo dolores et ea rebum." +
//            " Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.Duis autem vel eum" +
//            " iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eufeugiat nulla" +
//            " facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzrildelenit" +
//            " augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, ",
//        price = 4.99
//    ))
  }
}
