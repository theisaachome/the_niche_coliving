package com.theniche.colivin.service;

import com.theniche.colivin.payload.*;

import java.util.UUID;

public interface HouseService {
    HouseResponseDto findHouseById(UUID houseId);
    ApiResponse insertHouseWithRooms(HouseDto dto);
    ApiResponse updateHouse(UUID houseId, UpdateHouseDto dto);
    ApiResponse deleteHouse(UUID houseId);
    ApiResponse addHouseRoom(UUID houseId, RoomRequestDto dto); // optional

}
