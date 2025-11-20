package com.sica.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class CorsConfig {

    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${cors.allowed-methods}")
    private String allowedMethods;

    @Value("${cors.allowed-headers}")
    private String allowedHeaders;

    @Value("${cors.exposed-headers}")
    private String exposedHeaders;

    @Value("${cors.allow-credentials}")
    private Boolean allowCredentials = true;

    @Value("${cors.max-age}")
    private Long maxAge = 3600L;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                Arrays.stream(allowedOrigins.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList())
        );
        configuration.setAllowedMethods(
                Arrays.stream(allowedMethods.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList())
        );
        configuration.setAllowedHeaders(
                Arrays.stream(allowedHeaders.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList())
        );
        configuration.setExposedHeaders(
                Arrays.stream(exposedHeaders.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList())
        );
        configuration.setAllowCredentials(allowCredentials);
        configuration.setMaxAge(maxAge);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
