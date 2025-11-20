package com.sica.backend.dto;

import java.time.LocalDateTime;

public record VisitResponse(
    String id,
    String visitorName,
    LocalDateTime visitDatetime,
    String personVisited,
    String visitorPhoto,
    String status,
    String authorizedBy,
    LocalDateTime authorizedAt,
    String qrCodeBase64
) {}
