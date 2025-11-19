package com.sica.backend.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig {

    @Value("\${cors.allowed-origins}")
    private lateinit var allowedOrigins: String

    @Value("\${cors.allowed-methods}")
    private lateinit var allowedMethods: String

    @Value("\${cors.allowed-headers}")
    private lateinit var allowedHeaders: String

    @Value("\${cors.exposed-headers}")
    private lateinit var exposedHeaders: String

    @Value("\${cors.allow-credentials}")
    private var allowCredentials: Boolean = true

    @Value("\${cors.max-age}")
    private var maxAge: Long = 3600

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = allowedOrigins.split(",").map { it.trim() }
        configuration.allowedMethods = allowedMethods.split(",").map { it.trim() }
        configuration.allowedHeaders = allowedHeaders.split(",").map { it.trim() }
        configuration.exposedHeaders = exposedHeaders.split(",").map { it.trim() }
        configuration.allowCredentials = allowCredentials
        configuration.maxAge = maxAge

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
