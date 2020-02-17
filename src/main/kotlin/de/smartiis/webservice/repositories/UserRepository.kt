package de.smartiis.webservice.repositories

import de.smartiis.webservice.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, Int> {

  fun findByEmailAddress(emailAddress: String): Optional<User>
  fun existsByEmailAddress(emailAddress: String): Boolean
}
