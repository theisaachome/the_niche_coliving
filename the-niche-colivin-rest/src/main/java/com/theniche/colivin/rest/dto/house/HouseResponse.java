package com.theniche.colivin.rest.dto.house;

import com.theniche.colivin.domain.common.HouseStatus;
import com.theniche.colivin.rest.dto.room.RoomResponse;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record HouseResponse(
        UUID id,
        String houseName,
        String houseCode,
        String description,
        String address,
        String notes,
        HouseStatus houseStatus,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<RoomResponse> rooms
){
}
