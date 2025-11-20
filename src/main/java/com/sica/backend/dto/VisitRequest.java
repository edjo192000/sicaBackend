package com.sica.backend.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record VisitRequest(
    @NotBlank(message = "El nombre del visitante es requerido")
    String visitorName,

    LocalDateTime visitDatetime,

    @NotBlank(message = "La persona visitada es requerida")
    String personVisited
) {}
