package de.smartiis.webservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class EmailAlreadyInUseException : Exception("Email-Adresse wird bereits verwendet")

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "wrong-password")
class WrongPasswordException : Exception("Das eingegebene Passwort ist falsch")
