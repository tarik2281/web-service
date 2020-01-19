package de.smartiis.webservice.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Product (
    @Id
    var id: Int = 0,
    var title: String = "",
    var thumbnailUrl: String = "",
    var shortDescription: String = "",
    @Column(columnDefinition = "varchar(max)")
    var longDescription: String = "",
    var price: Double = 0.0
)
