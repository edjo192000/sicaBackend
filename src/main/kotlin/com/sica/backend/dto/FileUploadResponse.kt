package com.sica.backend.dto

data class FileUploadResponse(
    val success: Boolean,
    val url: String? = null,
    val fileUrl: String? = null,
    val error: String? = null
)
