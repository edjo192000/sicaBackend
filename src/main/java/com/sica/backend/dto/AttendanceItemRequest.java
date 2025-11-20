package com.sica.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record AttendanceItemRequest(
    @NotBlank(message = "El estudiante es requerido")
    String estudianteId,

    @NotBlank(message = "El estado es requerido")
    String estado
) {}
