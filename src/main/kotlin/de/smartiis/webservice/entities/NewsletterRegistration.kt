package de.smartiis.webservice.entities

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class NewsletterRegistration (
  @Id
  var emailAddress: String = ""
)
