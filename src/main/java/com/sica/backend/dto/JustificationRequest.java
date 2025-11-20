package com.sica.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JustificationRequest(
    @NotBlank(message = "La asistencia es requerida")
    String asistenciaId,

    @NotBlank(message = "El estudiante es requerido")
    String estudianteId,

    @NotBlank(message = "El profesor es requerido")
    String profesorId,

    @NotBlank(message = "La materia es requerida")
    String materiaId,

    @NotBlank(message = "La URL del archivo es requerida")
    String archivoUrl,

    @NotBlank(message = "El tipo de archivo es requerido")
    String archivoTipo,

    @NotBlank(message = "La descripción es requerida")
    @Size(min = 20, message = "La descripción debe tener al menos 20 caracteres")
    String descripcion
) {}
