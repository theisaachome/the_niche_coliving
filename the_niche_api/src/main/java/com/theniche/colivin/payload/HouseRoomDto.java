package com.theniche.colivin.payload;
import jakarta.validation.constraints.*;

public record HouseRoomDto(
        // Room number must not be empty and should be 4–10 characters (e.g., A101)
        @NotEmpty(message = "Room number must not be empty.")
        @Size(min = 4, max = 10, message = "Room number must be between 4 and 10 characters.")
        String room_number,
        // Capacity must not be null and should be at least 1
        @NotNull(message = "Capacity must not be null.")
        @Min(value = 1, message = "Capacity must be at least 1.")
        Integer capacity,
        String notes
) {
}
