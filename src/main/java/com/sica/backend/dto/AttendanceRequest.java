package com.sica.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record AttendanceRequest(
    @NotBlank(message = "La materia es requerida")
    String materiaId,

    @NotBlank(message = "El profesor es requerido")
    String profesorId,

    @NotNull(message = "La fecha es requerida")
    LocalDate fecha,

    @NotEmpty(message = "Debe haber al menos una asistencia")
    List<AttendanceItemRequest> asistencias
) {}
