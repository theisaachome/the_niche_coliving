package com.theniche.colivin.rest.dto.tenant;

import com.theniche.colivin.domain.common.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TenantResponse(
        UUID id,
        String tenantCode,
        String fullName,
        String phone,
        String email,
        Gender gender,
        LocalDate dateOfBirth,
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
