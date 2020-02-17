package de.smartiis.webservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "email-already-exists")
class EmailAlreadyInUseException : Exception("Email-Adresse wird bereits verwendet")

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "wrong-password")
class WrongPasswordException : Exception("Das eingegebene Passwort ist falsch")

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "newsletter-already-registered")
class NewsletterAlreadyRegisteredException : Exception("Email already registered for newsletter")
