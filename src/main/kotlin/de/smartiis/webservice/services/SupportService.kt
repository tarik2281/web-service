package de.smartiis.webservice.services

import de.smartiis.webservice.SecureIdGenerator
import de.smartiis.webservice.entities.NewsletterRegistration
import de.smartiis.webservice.entities.SupportTicket
import de.smartiis.webservice.exceptions.NewsletterAlreadyRegisteredException
import de.smartiis.webservice.repositories.NewsletterRepository
import de.smartiis.webservice.repositories.SupportTicketRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SupportService @Autowired constructor(
    private val supportTicketRepository: SupportTicketRepository,
    private val newsletterRepository: NewsletterRepository
) {

  fun addTicket(supportTicket: SupportTicket) {
    var id: Int
    do {
      id = SecureIdGenerator.next()
    } while (supportTicketRepository.existsById(id))

    supportTicket.id = id
    supportTicket.creationDate = Date()
    supportTicketRepository.save(supportTicket)
  }

  fun registerNewsletter(emailAddress: String) {
    if (newsletterRepository.existsById(emailAddress))
      throw NewsletterAlreadyRegisteredException()

    newsletterRepository.save(NewsletterRegistration(emailAddress))
  }
}
