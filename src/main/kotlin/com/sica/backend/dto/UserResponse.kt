package com.sica.backend.dto

import java.time.LocalDateTime

data class UserResponse(
    val id: String,
    val username: String,
    val name: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: String,
    val foto: String?,
    val matricula: String? = null,
    val numeroEmpleado: String? = null,
    val activo: Boolean,
    val creadoEn: LocalDateTime
)
