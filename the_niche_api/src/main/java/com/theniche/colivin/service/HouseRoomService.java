package com.theniche.colivin.service;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.HouseRoomDto;

import java.util.List;
import java.util.UUID;

public interface HouseRoomService {
    ApiResponse addHouseRoom(HouseRoomDto dto);
    List<HouseRoomDto> getHouseRoomsOfHouseById(UUID houseId);
}
