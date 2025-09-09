package com.theniche.colivin.rest.dto.house;

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
        Set<com.theniche.colivin.rest.dto.room.RoomResponseDto> rooms
){
}
