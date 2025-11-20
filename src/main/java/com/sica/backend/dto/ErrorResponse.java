package com.sica.backend.dto;

public record ErrorResponse(
    Boolean success,
    String error,
    String message,
    Long timestamp
) {
    public ErrorResponse(String error, String message) {
        this(false, error, message, System.currentTimeMillis());
    }

    public ErrorResponse(String error) {
        this(false, error, null, System.currentTimeMillis());
    }
}
