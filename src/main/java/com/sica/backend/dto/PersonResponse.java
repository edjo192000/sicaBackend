package com.sica.backend.dto;

public record PersonResponse(
    String id,
    String nombre,
    String tipo,
    String email,
    String foto,
    Boolean activo
) {}
