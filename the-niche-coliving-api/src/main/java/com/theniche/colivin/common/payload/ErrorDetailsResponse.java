package com.theniche.colivin.common.payload;

import java.time.LocalDateTime;

public class ErrorDetailsResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private String errorCode;

    public ErrorDetailsResponse() {}

    public ErrorDetailsResponse(LocalDateTime timestamp, String message, String details, String errorCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ErrorDetailsResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ErrorDetailsResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorDetailsResponse setDetails(String details) {
        this.details = details;
        return this;
    }

    public ErrorDetailsResponse setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
