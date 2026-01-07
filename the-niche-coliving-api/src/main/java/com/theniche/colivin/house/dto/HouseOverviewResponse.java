package com.theniche.colivin.house.dto;

import com.theniche.colivin.house.HouseStatus;

import java.util.UUID;

public record HouseOverviewResponse(
    UUID id,
    String houseCode,
    String name,
    String location,
    HouseStatus houseStatus,
    long totalRooms,
    long availableRooms
) {}
