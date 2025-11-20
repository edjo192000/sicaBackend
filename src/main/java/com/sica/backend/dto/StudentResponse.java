package com.sica.backend.dto;

public record StudentResponse(
    String id,
    String nombre,
    String matricula,
    String foto
) {}
