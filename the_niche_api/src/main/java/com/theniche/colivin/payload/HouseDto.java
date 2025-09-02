package com.theniche.colivin.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record HouseDto(
        // Unit name must not be null or empty and should have at least 7 characters
        @NotEmpty(message = "Unit name must not be empty.")
        @Size(min = 7, message = "Unit name must contain at least 7 characters.")
        String name,

        // Address must not be null or empty, and should be between 50–150 characters
        @NotEmpty(message = "Address must not be empty.")
        @Size(min = 50, max = 150, message = "Address must be between 50 and 150 characters.")
        String address,

        String description,
        String notes,

        // At least one room is required
        @NotEmpty(message = "At least one room is required.")
        @Size(min = 1, message = "At least one room must be provided.")
        List<RoomRequestDto> rooms
) {
}
