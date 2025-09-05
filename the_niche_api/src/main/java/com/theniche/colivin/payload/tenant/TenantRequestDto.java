package com.theniche.colivin.payload.tenant;

import com.theniche.colivin.entity.Gender;

import java.time.LocalDate;

public record TenantRequestDto(
        String fullName,
        Gender gender,
        LocalDate dateOfBirth
) {
}
