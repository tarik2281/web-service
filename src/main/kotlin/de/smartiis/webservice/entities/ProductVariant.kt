package de.smartiis.webservice.entities

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import javax.persistence.*

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

@Entity
data class ProductVariant (
    @Id
    var id: Int = 0,
    var productId: String = "",
    var title: String = "",
    var cartTitle: String = "",
    var shortDescription: String = "",
    @Column(columnDefinition = "varchar(max)")
    @Convert(converter = StringListConverter::class)
    var features: List<String> = mutableListOf(),
    @Column(columnDefinition = "varchar(max)")
    @Convert(converter = DescriptionConverter::class)
    var longDescription: List<Description> = mutableListOf(),
    var price: Double = 0.0,
    @Column(columnDefinition = "varchar(max)")
    @Convert(converter = StringListConverter::class)
    var images: List<String> = mutableListOf()
)
