package de.smartiis.webservice.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Product (
    @Id
    var idString: String = "",
    var title: String = "",
    var overviewDescription: String = "",
    @OneToMany(targetEntity = ProductVariant::class, mappedBy = "productId")
    var variants: List<ProductVariant> = mutableListOf()
)
