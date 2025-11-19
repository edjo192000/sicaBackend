package com.sica.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
class SicaBackendApplication

fun main(args: Array<String>) {
    runApplication<SicaBackendApplication>(*args)
}
