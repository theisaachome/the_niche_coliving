package com.theniche.colivin.common.payload;

import java.time.Instant;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data) {
}
