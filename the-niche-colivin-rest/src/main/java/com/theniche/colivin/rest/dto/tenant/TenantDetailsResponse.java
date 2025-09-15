package com.theniche.colivin.rest.dto.tenant;

import com.theniche.colivin.domain.entity.Gender;
import com.theniche.colivin.rest.dto.address.AddressDto;
import com.theniche.colivin.rest.dto.document.DocumentResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TenantDetailsResponse(
        UUID id,
        String tenantCode,
        String fullName,
        String phone,
        String email,
        Gender gender,
        LocalDate dateOfBirth,
        List<DocumentResponse> documents,
        List<AddressDto>  addresses,
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
