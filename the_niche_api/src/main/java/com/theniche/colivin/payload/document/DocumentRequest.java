package com.theniche.colivin.payload.document;

import jakarta.validation.constraints.NotEmpty;

public record DocumentRequest(
        @NotEmpty(message = "Document Type required")
        String documentType,
        @NotEmpty(message = "Document Number required")
        String documentNumber
) {
}
