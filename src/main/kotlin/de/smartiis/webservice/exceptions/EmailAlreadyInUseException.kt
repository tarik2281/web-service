package de.smartiis.webservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class EmailAlreadyInUseException : Exception("Email-Adresse wird bereits verwendet")
