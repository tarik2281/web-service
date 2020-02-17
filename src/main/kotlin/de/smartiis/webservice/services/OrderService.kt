package de.smartiis.webservice.services

import de.smartiis.webservice.SecureIdGenerator
import de.smartiis.webservice.entities.OrderC
import de.smartiis.webservice.entities.ProductVariant
import de.smartiis.webservice.repositories.OrderEntryRepository
import de.smartiis.webservice.repositories.OrderRepository
import de.smartiis.webservice.repositories.ProductVariantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService @Autowired constructor(
    private val orderRepository: OrderRepository,
    private val productVariantRepository: ProductVariantRepository
) {

  fun getAll() = orderRepository.findAll()

  fun getAllForUser(userId: Int) = orderRepository.findByUserId(userId)

  fun placeOrder(order: OrderC) {
    var id: Int
    do {
      id = SecureIdGenerator.next()
    } while (orderRepository.existsById(id))

    order.id = id

    for (entry in order.entries) {
      entry.orderId = id

      val productVariant = productVariantRepository.findByIdOrNull(entry.productVariantId)
      if (productVariant != null) {
        entry.price = productVariant.price
      }
    }

    order.state = "In Bearbeitung"

    orderRepository.save(order)
  }

  fun requestCancel(orderId: Int) {
    val order = orderRepository.findByIdOrNull(orderId)
    if (order != null) {
      order.state = "Stornierung ausstehend"
      orderRepository.save(order)
    }
  }
}
