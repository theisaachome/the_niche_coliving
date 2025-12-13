package com.theniche.colivin.payload.tenant;

import com.theniche.colivin.entity.Gender;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record TenantRequestDto(
        @NotEmpty(message = "Name required")
        String fullName,
        @NotEmpty(message = "Phone required")
        String phone,
        String email,
        Gender gender,
        LocalDate dateOfBirth
) {
}
