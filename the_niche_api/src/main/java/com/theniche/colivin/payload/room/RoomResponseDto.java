package com.theniche.colivin.payload.room;

import com.theniche.colivin.payload.BaseResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record RoomResponseDto(
        UUID id,
        // Room number must not be empty and should be 4–10 characters (e.g., A101)
        @NotEmpty(message = "Room number must not be empty.")
        @Size(min = 4, max = 10, message = "Room number must be between 4 and 10 characters.")
        String room_number,
        // Capacity must not be null and should be at least 1
        @NotNull(message = "Capacity must not be null.")
        @Min(value = 1, message = "Capacity must be at least 1.")
        Integer capacity,
        String notes,
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) implements BaseResponse {
}
