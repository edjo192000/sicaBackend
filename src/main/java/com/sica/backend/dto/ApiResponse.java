package com.sica.backend.dto;

public record ApiResponse<T>(
    Boolean success,
    String message,
    T data
) {
    public ApiResponse(T data) {
        this(true, null, data);
    }

    public ApiResponse(Boolean success, String message) {
        this(success, message, null);
    }
}
