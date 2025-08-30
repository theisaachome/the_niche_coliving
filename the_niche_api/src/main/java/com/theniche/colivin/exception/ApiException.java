package com.theniche.colivin.exception;

import org.springframework.http.HttpStatus;

public class ApiException  extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    public ApiException(String msg,HttpStatus status, String message) {
        super(msg);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
