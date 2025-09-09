package com.theniche.colivin.rest;

public record ApiResponse<T>(
        String status,
        String message,
        T data
)  {
}
