package de.smartiis.webservice.entities

import java.util.*
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

@Entity
data class OrderC(
    @Id
    var id: Int = 0,
    var userId: Int = 0,
    var emailAddress: String = "",
    var sex: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var birthday: Date = Date(),
    var phoneNumber: String = "",
    @Column(columnDefinition = "varchar(max)")
    @Convert(converter = AddressConverter::class)
    var shippingAddress: Address = Address(),
    var useDualAddress: Boolean = true,
    @Column(columnDefinition = "varchar(max)")
    @Convert(converter = AddressConverter::class)
    var billingAddress: Address = Address(),
    @OneToMany(targetEntity = OrderEntry::class, mappedBy = "orderId", cascade = [CascadeType.ALL])
    var entries: List<OrderEntry> = mutableListOf(),
    var state: String = "",
    var orderDate: Date = Date()
)
