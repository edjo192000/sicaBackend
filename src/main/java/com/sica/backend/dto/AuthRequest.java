package com.sica.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    @NotBlank(message = "El nombre de usuario es requerido")
    String username,

    @NotBlank(message = "La contraseña es requerida")
    String password
) {}
