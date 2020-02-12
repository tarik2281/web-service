package de.smartiis.webservice.entities

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class SupportTicket (
  @Id
  var id: Int = 0,
  var userName: String? = null,
  var emailAddress: String = "",
  var category: String = "",
  var description: String = ""
)
