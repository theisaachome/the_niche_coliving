package com.theniche.colivin.service;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.RoomRequestDto;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    ApiResponse addHouseRoom(RoomRequestDto dto);
    List<RoomRequestDto> getHouseRoomsOfHouseById(UUID houseId);
//    void addRoomToHouse();
//    void getRoom()
//ApiResponse updateRoom(UUID roomId, UpdateRoomDto dto)
//ApiResponse deleteRoom(UUID roomId)
//List<RoomResponseDto> searchRooms(RoomFilter filter)
}
