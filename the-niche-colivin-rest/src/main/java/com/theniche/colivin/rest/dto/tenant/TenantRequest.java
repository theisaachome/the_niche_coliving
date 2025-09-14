package com.theniche.colivin.rest.dto.tenant;
import com.theniche.colivin.domain.entity.Gender;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record TenantRequest(
        @NotEmpty(message = "Name required")
        String fullName,
        @NotEmpty(message = "Phone required")
        String phone,
        String email,
        Gender gender,
        LocalDate dateOfBirth
) {
}
