package com.sica.backend.dto

data class StudentResponse(
    val id: String,
    val nombre: String,
    val matricula: String,
    val foto: String?
)
