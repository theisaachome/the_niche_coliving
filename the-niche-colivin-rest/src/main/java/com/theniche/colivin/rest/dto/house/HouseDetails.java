package com.theniche.colivin.rest.dto.house;

import com.theniche.colivin.domain.common.HouseStatus;
import com.theniche.colivin.rest.dto.room.RoomResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record HouseDetails(
        UUID id,
        String houseName,
        String houseCode,
        String remark,
        HouseStatus houseStatus,
        List<RoomResponse> roomOptions,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
