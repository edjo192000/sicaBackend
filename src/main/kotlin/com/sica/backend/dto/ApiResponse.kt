package com.sica.backend.dto

data class ApiResponse<T>(
    val success: Boolean = true,
    val message: String? = null,
    val data: T? = null
)

data class ErrorResponse(
    val success: Boolean = false,
    val error: String,
    val message: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
