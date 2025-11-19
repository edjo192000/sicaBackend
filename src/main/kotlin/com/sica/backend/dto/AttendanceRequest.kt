package com.sica.backend.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class AttendanceRequest(
    @field:NotBlank(message = "La materia es requerida")
    val materiaId: String,
    
    @field:NotBlank(message = "El profesor es requerido")
    val profesorId: String,
    
    @field:NotNull(message = "La fecha es requerida")
    val fecha: LocalDate,
    
    @field:NotEmpty(message = "Debe haber al menos una asistencia")
    val asistencias: List<AttendanceItemRequest>
)

data class AttendanceItemRequest(
    @field:NotBlank(message = "El estudiante es requerido")
    val estudianteId: String,
    
    @field:NotBlank(message = "El estado es requerido")
    val estado: String
)
