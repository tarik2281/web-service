package de.smartiis.webservice.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import de.smartiis.webservice.Address
import de.smartiis.webservice.AddressConverter
import java.sql.Date
import javax.persistence.*

@JsonIgnoreProperties(value = ["password"], allowSetters = true)
@Entity
data class User(
    @Id
    var id: Int = 0,
    var emailAddress: String = "",
    var password: String = "",
    var sex: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var birthday: Date = Date(0),
    var phoneNumber: String = "",
    @Column(columnDefinition = "TEXT")
    @Convert(converter = AddressConverter::class)
    var shippingAddress: Address = Address(),
    var useDualAddress: Boolean = true,
    @Column(columnDefinition = "TEXT")
    @Convert(converter = AddressConverter::class)
    var billingAddress: Address = Address()
)

