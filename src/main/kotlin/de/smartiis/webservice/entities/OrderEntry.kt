package de.smartiis.webservice.entities

import javax.persistence.*

@Entity
data class OrderEntry(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int = 0,
    var orderId: Int = 0,
    @Column(name = "product_variant_id")
    var productVariantId: Int = 0,
    var amount: Int = 0,
    var price: Double = 0.0
)
