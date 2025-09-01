package com.theniche.colivin.service;

import com.theniche.colivin.entity.House;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.HouseDto;
import com.theniche.colivin.payload.HouseResponseDto;
import com.theniche.colivin.payload.UpdateHouseDto;

import java.util.UUID;

public interface HouseService {
    HouseResponseDto findHouseById(UUID houseId);
    ApiResponse registerHouse(HouseDto dto);
    ApiResponse updateHouse(UUID houseId, UpdateHouseDto dto);
    ApiResponse deleteHouse(UUID houseId);


}
