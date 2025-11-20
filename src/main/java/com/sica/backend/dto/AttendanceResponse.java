package com.sica.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AttendanceResponse(
    String id,
    String estudianteId,
    String estudianteNombre,
    String estudianteFoto,
    String profesorId,
    String profesorNombre,
    String materiaId,
    String materiaNombre,
    LocalDate fecha,
    String estado,
    Boolean justificado,
    LocalDateTime creadoEn,
    LocalDateTime actualizadoEn
) {}
