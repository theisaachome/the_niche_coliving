package com.theniche.colivin.payload.account;

import java.time.LocalDateTime;

public record AccountResponse(
        String accountId,
        LocalDateTime createdDate,
        String createdBy
) {
}
