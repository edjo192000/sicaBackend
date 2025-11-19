package com.sica.backend.dto

data class PersonResponse(
    val id: String,
    val nombre: String,
    val tipo: String,
    val email: String,
    val foto: String?,
    val activo: Boolean
)
