package com.theniche.colivin.service;

import com.theniche.colivin.payload.*;
import com.theniche.colivin.payload.house.HouseDto;
import com.theniche.colivin.payload.house.HouseResponseDto;
import com.theniche.colivin.payload.house.UpdateHouseDto;
import com.theniche.colivin.payload.room.RoomRequestDto;

import java.util.UUID;

public interface HouseService {
    ApiResponse<HouseResponseDto> findHouseById(UUID houseId);
    ApiResponse<ResponseData> insertHouseWithRooms(HouseDto dto);
    ApiResponse<ResponseData> updateHouse(UUID houseId, UpdateHouseDto dto);
    ApiResponse<ResponseData> deleteHouse(UUID houseId);
    ApiResponse<ResponseData> softDeleteHouse(UUID houseId);
    ApiResponse<ResponseData> addHouseRoom(UUID houseId, RoomRequestDto dto); // optional

}
