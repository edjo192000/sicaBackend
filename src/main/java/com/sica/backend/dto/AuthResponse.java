package com.sica.backend.dto;

public record AuthResponse(
    String id,
    String username,
    String name,
    String role,
    String email,
    String foto,
    String token,
    String refreshToken
) {}
