package com.nemesiss.dev.springwithkotlin.Controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@EnableAutoConfiguration
@RestController("/")
class HelloController {

    private val logger = loggerFor(this.javaClass)

    @RequestMapping("hello")
    fun Hello(session: HttpSession): String {
        logger.info("Accessing visited counter API!")
        return "Hello, KotlinEE! You have visited this API " + session.getAndIncrement("visited", 1) + " time(s)"
    }

}

fun <T> HttpSession.getAndIncrement(key: String, value: T): Number where T : Number, T : Comparable<T> {
    val initValue = getAttribute(key)
    if (initValue is Number) {
        when (initValue) {
            is Float, is Double -> {
                setAttribute(key, initValue.toDouble() + value.toDouble())
            }
            is Int, is Long, is Short -> {
                setAttribute(key, initValue.toLong() + value.toLong())
            }
        }
        return initValue
    } else {
        setAttribute(key, value)
    }
    return 0
}

inline fun <reified T : Any> loggerFor(clazz: Class<T>): Logger {
    return LoggerFactory.getLogger(clazz)
}