package com.sica.backend.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class AttendanceResponse(
    val id: String,
    val estudianteId: String? = null,
    val estudianteNombre: String? = null,
    val estudianteFoto: String? = null,
    val profesorId: String? = null,
    val profesorNombre: String? = null,
    val materiaId: String,
    val materiaNombre: String,
    val fecha: LocalDate,
    val estado: String,
    val justificado: Boolean,
    val creadoEn: LocalDateTime? = null,
    val actualizadoEn: LocalDateTime? = null
)
