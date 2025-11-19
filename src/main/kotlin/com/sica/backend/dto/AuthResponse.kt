package com.sica.backend.dto

data class AuthResponse(
    val id: String,
    val username: String,
    val name: String,
    val role: String,
    val email: String,
    val foto: String?,
    val token: String,
    val refreshToken: String? = null
)
