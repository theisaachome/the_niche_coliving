package com.theniche.colivin.payload.house;

import com.theniche.colivin.payload.BaseResponse;
import com.theniche.colivin.payload.room.RoomResponseDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record HouseResponseDto(
        UUID id,
        String houseName,
        String description,
        String address,
        String notes,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<RoomResponseDto> rooms
) implements BaseResponse {
}
