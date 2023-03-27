package edu.spring.btp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BtpApplication

fun main(args: Array<String>) {
    runApplication<BtpApplication>(*args)
}