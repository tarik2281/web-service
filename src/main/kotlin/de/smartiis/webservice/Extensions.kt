package de.smartiis.webservice

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Date
import javax.persistence.AttributeConverter
import javax.persistence.Converter

fun Any.getLogger(): Logger = LoggerFactory.getLogger(this.javaClass)

data class Description (
    var title: String = "",
    var content: String = ""
)

private val objectMapper = ObjectMapper()
private const val SPLIT_CHAR = "|"

@Converter
class StringListConverter : AttributeConverter<List<String>, String> {
  override fun convertToDatabaseColumn(attribute: List<String>): String {
    return attribute.joinToString(separator = SPLIT_CHAR)
  }

  override fun convertToEntityAttribute(dbData: String): List<String> {
    if (dbData.isBlank())
      return emptyList()

    return dbData.split(SPLIT_CHAR)
  }
}

@Converter
class DescriptionConverter : AttributeConverter<List<Description>, String> {

  override fun convertToDatabaseColumn(attribute: List<Description>): String {
    return objectMapper.writeValueAsString(attribute)
  }

  override fun convertToEntityAttribute(dbData: String): List<Description> {
    return objectMapper.readValue(dbData, object : TypeReference<List<Description>>() { })
  }
}

data class Address(
    var street: String = "",
    var postalCode: String = "",
    var city: String = "",
    var country: String = ""
)

@Converter
class AddressConverter : AttributeConverter<Address, String> {

  override fun convertToDatabaseColumn(attribute: Address): String {
    return objectMapper.writeValueAsString(attribute)
  }

  override fun convertToEntityAttribute(dbData: String): Address {
    return objectMapper.readValue(dbData, object : TypeReference<Address>() { })
  }
}



data class RegisterUserData(
    var emailAddress: String = "",
    var newPassword: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var sex: String = "",
    var birthday: Date = Date(0)
)
