package com.sica.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PersonRequest(
    @NotBlank(message = "El tipo es requerido")
    String tipo,

    @NotBlank(message = "El nombre es requerido")
    String nombre,

    @NotBlank(message = "El apellido paterno es requerido")
    String apellidoPaterno,

    String apellidoMaterno,

    @NotBlank(message = "El email es requerido")
    @Email(message = "El email debe ser válido")
    String email,

    String telefono,

    // Estudiante
    String matricula,
    String nivel,
    String divisionId,
    String carreraId,

    // Empleado
    String numeroEmpleado,
    String areaId,

    // Común
    String turno,
    String horaEntrada,
    String horaSalida
) {}
