package com.sica.backend.dto

import jakarta.validation.constraints.NotBlank

data class AuthRequest(
    @field:NotBlank(message = "El nombre de usuario es requerido")
    val username: String,
    
    @field:NotBlank(message = "La contraseña es requerida")
    val password: String
)
