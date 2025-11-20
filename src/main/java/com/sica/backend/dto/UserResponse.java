package com.sica.backend.dto;

import java.time.LocalDateTime;

public record UserResponse(
    String id,
    String username,
    String name,
    String firstName,
    String lastName,
    String email,
    String role,
    String foto,
    String matricula,
    String numeroEmpleado,
    Boolean activo,
    LocalDateTime creadoEn
) {}
