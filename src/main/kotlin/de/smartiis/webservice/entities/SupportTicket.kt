package de.smartiis.webservice.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class SupportTicket (
  @Id
  var id: Int = 0,
  var creationDate: Date = Date(0),
  var name: String? = null,
  var emailAddress: String = "",
  var message: String = ""
)
