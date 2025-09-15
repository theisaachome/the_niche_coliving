package com.theniche.colivin.rest.dto.address;


import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.UUID;

public record AddressDto(
        UUID id,
        @NotEmpty(message = "addressLine1 can not be empty")
        String addressLine1,
        String addressLine2,
        @NotEmpty(message="city can not be empty")
        String city,
        @NotEmpty(message = "state can not be empty")
        String state,
        String postalCode,
        @NotEmpty(message = "country can not be empty")
        String country,
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
){
}

