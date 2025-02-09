package com.xyz.reviewsystem.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDto {

    private final String apiPath;
    private final HttpStatus errorCode;
    private final String errorMessage;
    private final LocalDateTime errorTime;

    public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime;
    }

    public String getApiPath() {
        return apiPath;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }
}
