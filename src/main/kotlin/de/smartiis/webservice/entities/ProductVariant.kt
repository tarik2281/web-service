package de.smartiis.webservice.entities

import de.smartiis.webservice.Description
import de.smartiis.webservice.DescriptionConverter
import de.smartiis.webservice.StringListConverter
import javax.persistence.*

@Entity
data class ProductVariant (
    @Id
    var id: Int = 0,
    var productId: String = "",
    var title: String = "",
    var cartTitle: String = "",
    var shortDescription: String = "",
    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter::class)
    var features: List<String> = mutableListOf(),
    @Column(columnDefinition = "TEXT")
    @Convert(converter = DescriptionConverter::class)
    var longDescription: List<Description> = mutableListOf(),
    var price: Double = 0.0,
    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter::class)
    var images: List<String> = mutableListOf()
)

