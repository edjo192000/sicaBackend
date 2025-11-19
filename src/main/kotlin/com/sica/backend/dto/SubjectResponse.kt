package com.sica.backend.dto

data class SubjectResponse(
    val id: String,
    val nombre: String,
    val codigo: String,
    val profesorId: String? = null,
    val profesorNombre: String? = null,
    val estudiantesIds: List<String>? = null
)
