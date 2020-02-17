package de.smartiis.webservice.entities

import de.smartiis.webservice.Address
import de.smartiis.webservice.AddressConverter
import java.util.*
import javax.persistence.*

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
    @Column(columnDefinition = "TEXT")
    @Convert(converter = AddressConverter::class)
    var shippingAddress: Address = Address(),
    var useDualAddress: Boolean = true,
    @Column(columnDefinition = "TEXT")
    @Convert(converter = AddressConverter::class)
    var billingAddress: Address = Address(),
    @OneToMany(targetEntity = OrderEntry::class, mappedBy = "orderId", cascade = [CascadeType.ALL])
    var entries: List<OrderEntry> = mutableListOf(),
    var state: String = "",
    var orderDate: Date = Date()
)
