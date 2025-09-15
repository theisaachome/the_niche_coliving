package com.theniche.colivin.rest.dto.tenant;
import com.theniche.colivin.domain.entity.Gender;
import com.theniche.colivin.rest.dto.address.AddressDto;
import com.theniche.colivin.rest.dto.document.DocumentRequest;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;


public record TenantRequest(
        @NotEmpty(message = "Name required")
        String fullName,
        @NotEmpty(message = "Phone required")
        String phone,
        String email,
        Gender gender,
        Set<AddressDto> addresses,
        Set<DocumentRequest> documents,
        LocalDate dateOfBirth
) {
}
