package com.theniche.colivin.payload;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseData(
        UUID id,
        String name,
        LocalDateTime createdTime,
        String createdBy
) {
}
