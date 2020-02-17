package de.smartiis.webservice.repositories

import de.smartiis.webservice.entities.NewsletterRegistration
import de.smartiis.webservice.entities.SupportTicket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsletterRepository : JpaRepository<NewsletterRegistration, String> {

}

@Repository
interface SupportTicketRepository : JpaRepository<SupportTicket, Int> {

}
