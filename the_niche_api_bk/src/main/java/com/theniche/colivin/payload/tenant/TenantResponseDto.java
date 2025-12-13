package com.theniche.colivin.payload.tenant;

import com.theniche.colivin.entity.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TenantResponseDto(
        UUID id,
        String fullName,
        String phone,
        String email,
        Gender gender,
        LocalDate dateOfBirth,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
