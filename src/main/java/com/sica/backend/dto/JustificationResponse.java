package com.sica.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record JustificationResponse(
    String id,
    String asistenciaId,
    String estudianteId,
    String estudianteNombre,
    String estudianteFoto,
    String profesorId,
    String materiaId,
    String materiaNombre,
    LocalDate fecha,
    String archivoUrl,
    String archivoTipo,
    String descripcion,
    String estado,
    String comentarioProfesor,
    LocalDateTime creadoEn,
    LocalDateTime revisadoEn
) {}
