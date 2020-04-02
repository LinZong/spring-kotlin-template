package com.nemesiss.dev.springwithkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWithKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringWithKotlinApplication>(*args)
}
