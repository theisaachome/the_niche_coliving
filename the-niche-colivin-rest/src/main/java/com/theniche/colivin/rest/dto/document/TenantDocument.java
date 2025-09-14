package com.theniche.colivin.rest.dto.document;

import java.time.LocalDateTime;

public record TenantDocument(

        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
