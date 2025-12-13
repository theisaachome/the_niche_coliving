package com.theniche.colivin.payload.house;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdateHouseDto(
        @NotEmpty
        @Size(min = 7) String name,
        @NotEmpty @Size(min = 50, max = 150) String address,
        String description,
        String notes
) {
}
