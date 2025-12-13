package com.theniche.colivin.payload.document;
import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentResponse(
        UUID id,
        String documentType,
        String documentNumber,
        String filePath,
        LocalDateTime uploadedAt,
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
