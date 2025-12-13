package com.theniche.colivin.payload.address;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.BaseResponse;

import java.time.LocalDateTime;

public record AddressDto(
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String postalCode,
        String country,
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) implements BaseResponse {
}

