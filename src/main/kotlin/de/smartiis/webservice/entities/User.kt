package de.smartiis.webservice.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.sql.Date
import javax.persistence.*

data class Address(
    var street: String = "",
    var postalCode: String = "",
    var city: String = "",
    var country: String = ""
)

private val objectMapper = ObjectMapper()

@Converter
class AddressConverter : AttributeConverter<Address, String> {

    override fun convertToDatabaseColumn(attribute: Address): String {
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String): Address {
        return objectMapper.readValue(dbData, object : TypeReference<Address>() { })
    }
}

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
    @Column(columnDefinition = "varchar(max)")
    @Convert(converter = AddressConverter::class)
    var shippingAddress: Address = Address(),
    var useDualAddress: Boolean = true,
    @Column(columnDefinition = "varchar(max)")
    @Convert(converter = AddressConverter::class)
    var billingAddress: Address? = null
)
