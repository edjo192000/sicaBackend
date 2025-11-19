package com.sica.backend.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class PersonRequest(
    @field:NotBlank(message = "El tipo es requerido")
    val tipo: String,
    
    @field:NotBlank(message = "El nombre es requerido")
    val nombre: String,
    
    @field:NotBlank(message = "El apellido paterno es requerido")
    val apellidoPaterno: String,
    
    val apellidoMaterno: String? = null,
    
    @field:NotBlank(message = "El email es requerido")
    @field:Email(message = "El email debe ser válido")
    val email: String,
    
    val telefono: String? = null,
    
    // Estudiante
    val matricula: String? = null,
    val nivel: String? = null,
    val divisionId: String? = null,
    val carreraId: String? = null,
    
    // Empleado
    val numeroEmpleado: String? = null,
    val areaId: String? = null,
    
    // Común
    val turno: String? = null,
    val horaEntrada: String? = null,
    val horaSalida: String? = null
)
