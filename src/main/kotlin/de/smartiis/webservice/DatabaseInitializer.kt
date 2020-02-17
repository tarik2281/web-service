package de.smartiis.webservice

import de.smartiis.webservice.entities.*
import de.smartiis.webservice.repositories.OrderRepository
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
    private val orderRepository: OrderRepository,
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
        phoneNumber = "testPhoneNumber",
        shippingAddress = Address(
            "testStreet",
            "testPostalCode",
            "testCity",
            "testCountry"
        )))


    productRepository.save(Product(
        idString = "smarttub",
        title = "SmartTub",
        overviewImage = "assets/WerbephotoSmartTub2.png",
        overviewDescription = "Die intelligente Badewannenarmatur mit integrierter Badezusatzdosierung und vielen weiteren Features"
    ))

    productVariantRepository.save(ProductVariant(
        id = 1,
        productId = "smarttub",
        title = "SmartTub",
        cartTitle = "SmartTub",
        price = 49.99,
        images = listOf("assets/WerbephotoSmartTub2.png", "assets/WerbephotoSmartTub.png", "assets/Funktionsbeschreibung1.PNG", "assets/Funktionsbeschreibung2 (alt2).PNG", "assets/Funktionsbeschreibung3.PNG"),
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
        overviewImage = "assets/cherry.png",
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

    productRepository.save(Product(
        idString = "hub",
        title = "SmarTIIS Hub",
        overviewImage = "assets/SmarTIIS Hub_transparent.png",
        overviewDescription = "Die Intelligenz hinter deinem SmartHome"
    ))

    productVariantRepository.save(ProductVariant(
        id = SecureIdGenerator.next(),
        productId = "hub",
        title = "SmarTIIS Hub",
        cartTitle = "SmarTIIS Hub",
        price = 29.99,
        shortDescription = "Die Intelligenz hinter deinem SmartHome",
        images = listOf("assets/SmarTIIS Hub_transparent.png", "assets/PairingScreen.png", "assets/ScreenMyDevices.png")
    ))


    orderRepository.save(OrderC(
        1, 0, "a", "a", "a", "a", Date(0), "", Address(), true, Address(),
        mutableListOf(OrderEntry(
            0, 1, 1, 1, 49.99
        ))
    ))
  }
}
