package com.theniche.colivin.payload;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiResponse(
      String status,
      Data data
) {
    public record Data(
            UUID id,
            String name,
            LocalDateTime createdTime,
            String createdBy
    ){}
}
