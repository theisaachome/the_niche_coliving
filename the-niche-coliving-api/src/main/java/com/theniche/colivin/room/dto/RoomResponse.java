package com.theniche.colivin.room.dto;

import com.theniche.colivin.room.RoomStatus;
import com.theniche.colivin.room.RoomType;

import java.util.UUID;

public record RoomResponse(
        UUID id,
        String roomNumber,
        RoomType roomType,
        String roomCode,
        String capacity,
        String notes,
        RoomStatus roomStatus
) {
}
