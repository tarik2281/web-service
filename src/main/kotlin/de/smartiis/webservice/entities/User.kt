package de.smartiis.webservice.entities

import java.sql.Date
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
    @Id
    var id: Int = 0,
    var emailAddress: String = "",
    var password: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var birthday: Date = Date(0),
    var address: String = "",
    var postalCode: String = "",
    var city: String = "",
    var country: String = "",
    var phoneNumber: String = "",
    var newsletter: Boolean = false
)
