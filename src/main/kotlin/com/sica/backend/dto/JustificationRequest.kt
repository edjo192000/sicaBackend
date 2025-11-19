package com.sica.backend.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class JustificationRequest(
    @field:NotBlank(message = "La asistencia es requerida")
    val asistenciaId: String,
    
    @field:NotBlank(message = "El estudiante es requerido")
    val estudianteId: String,
    
    @field:NotBlank(message = "El profesor es requerido")
    val profesorId: String,
    
    @field:NotBlank(message = "La materia es requerida")
    val materiaId: String,
    
    @field:NotBlank(message = "La URL del archivo es requerida")
    val archivoUrl: String,
    
    @field:NotBlank(message = "El tipo de archivo es requerido")
    val archivoTipo: String,
    
    @field:NotBlank(message = "La descripción es requerida")
    @field:Size(min = 20, message = "La descripción debe tener al menos 20 caracteres")
    val descripcion: String
)
