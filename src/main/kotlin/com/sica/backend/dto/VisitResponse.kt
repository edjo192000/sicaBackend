package com.sica.backend.dto

import java.time.LocalDateTime

data class VisitResponse(
    val id: String,
    val visitorName: String,
    val visitDatetime: LocalDateTime,
    val personVisited: String,
    val visitorPhoto: String,
    val status: String,
    val authorizedBy: String? = null,
    val authorizedAt: LocalDateTime? = null,
    val qrCodeBase64: String? = null
)
