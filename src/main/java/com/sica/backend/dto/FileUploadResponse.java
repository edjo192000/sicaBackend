package com.sica.backend.dto;

public record FileUploadResponse(
    Boolean success,
    String url,
    String fileUrl,
    String error
) {}
