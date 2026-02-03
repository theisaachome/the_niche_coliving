package com.theniche.colivin.house.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HouseCreateRequest(
        @NotEmpty
        @Size(min = 6, max = 100,message = "House name should be at lease 6 characters.")
        String name,
        @NotNull
        @Size(min = 6, max = 100,message = "Location should be at lease 6 characters.")
        String location,
        String remark
) {
}
