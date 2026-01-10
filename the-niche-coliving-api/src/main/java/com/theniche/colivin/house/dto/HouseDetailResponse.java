package com.theniche.colivin.house.dto;

import com.theniche.colivin.house.HouseStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record HouseDetailResponse(
        UUID id,
        String houseCode,
        String name,
        String location,
        String remark,
        HouseStatus status,
        // Todo: show room list
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
){}
