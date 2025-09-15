package com.theniche.colivin.rest.dto.tenant;

import com.theniche.colivin.domain.entity.Gender;
import com.theniche.colivin.rest.dto.address.AddressDto;
import com.theniche.colivin.rest.dto.document.DocumentResponse;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record TenantResponse(
        UUID id,
        @NotEmpty(message = "Name required")
        String fullName,
        @NotEmpty(message = "Phone required")
        String phone,
        String email,
        Gender gender,
        Set<DocumentResponse> documents,
        Set<AddressDto> addresses,
        LocalDate dateOfBirth,
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
