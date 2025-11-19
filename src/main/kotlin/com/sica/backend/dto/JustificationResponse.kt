package com.sica.backend.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class JustificationResponse(
    val id: String,
    val asistenciaId: String,
    val estudianteId: String? = null,
    val estudianteNombre: String? = null,
    val estudianteFoto: String? = null,
    val profesorId: String? = null,
    val materiaId: String,
    val materiaNombre: String,
    val fecha: LocalDate? = null,
    val archivoUrl: String,
    val archivoTipo: String,
    val descripcion: String,
    val estado: String,
    val comentarioProfesor: String? = null,
    val creadoEn: LocalDateTime,
    val revisadoEn: LocalDateTime? = null
)
