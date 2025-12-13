package com.theniche.colivin.service;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.room.RoomRequestDto;
import com.theniche.colivin.payload.room.RoomResponseDto;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    ApiResponse<ResponseData> addRoomToHouse(RoomRequestDto dto);
    List<RoomRequestDto> getRoomsOfHouseById(UUID houseId);
    ApiResponse<RoomResponseDto> getRoomDetails(UUID roomId);
    ApiResponse<ResponseData> updateRoom(UUID roomId, RoomRequestDto dto);
    ApiResponse<ResponseData> deleteRoom(UUID roomId);
    List<RoomResponseDto> searchRooms();
}
