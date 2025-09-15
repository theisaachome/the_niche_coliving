package com.theniche.colivin.domain.exception;

public class BadRequestException extends RuntimeException {
    private String message;
    private String status;

    public BadRequestException(String message) {
        super(message);
        this.message = message;

    }
}
