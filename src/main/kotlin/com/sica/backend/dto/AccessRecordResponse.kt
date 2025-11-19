package com.sica.backend.dto

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class AccessRecordResponse(
    val id: String,
    val usuarioId: String,
    val usuarioNombre: String,
    val usuarioFoto: String?,
    val tipo: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val dispositivo: String,
    val ubicacion: String,
    val metodo: String,
    val creadoEn: LocalDateTime
)
