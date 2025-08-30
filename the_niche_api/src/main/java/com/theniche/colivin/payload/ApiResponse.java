package com.theniche.colivin.payload;

public record ApiResponse(
        String message,
        Integer statusCode,
        Boolean status,
        ResponseId id
) {
}
