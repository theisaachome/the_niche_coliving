package com.theniche.colivin.tenants.dto;

import com.theniche.colivin.common.domain.Gender;
import jakarta.validation.constraints.NotEmpty;

public record TenantRequest(
        @NotEmpty(message = "Name required")
        String fullName,
        @NotEmpty(message = "Phone required")
        String phone,
        String email,
        Gender gender){}
