package com.theniche.colivin.rest.dto.room;
import com.theniche.colivin.domain.common.RoomStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record RoomResponse(
        UUID id,
        String room_number,
        String roomCode,
        Integer capacity,
        String notes,
        RoomStatus roomStatus,
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
){
}
