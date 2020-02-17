package de.smartiis.webservice.repositories

import de.smartiis.webservice.entities.OrderC
import de.smartiis.webservice.entities.OrderEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderEntryRepository : JpaRepository<OrderEntry, Int> {


}

@Repository
interface OrderRepository : JpaRepository<OrderC, Int> {
  fun findByUserId(userId: Int): List<OrderC>
}
