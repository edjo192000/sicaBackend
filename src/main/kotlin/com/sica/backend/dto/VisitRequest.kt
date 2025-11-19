package com.sica.backend.dto

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class VisitRequest(
    @field:NotBlank(message = "El nombre del visitante es requerido")
    val visitorName: String,
    
    val visitDatetime: LocalDateTime,
    
    @field:NotBlank(message = "La persona visitada es requerida")
    val personVisited: String
)
