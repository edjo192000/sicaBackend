package com.sica.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AccessRecordResponse(
    String id,
    String usuarioId,
    String usuarioNombre,
    String usuarioFoto,
    String tipo,
    LocalDate fecha,
    LocalTime hora,
    String dispositivo,
    String ubicacion,
    String metodo,
    LocalDateTime creadoEn
) {}
