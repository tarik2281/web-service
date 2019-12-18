package de.smartiis.webservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebServiceApplication

fun main(args: Array<String>) {
    runApplication<WebServiceApplication>(*args)
}
