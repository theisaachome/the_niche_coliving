package com.theniche.colivin.room;
import com.theniche.colivin.room.dto.RoomRequest;
import com.theniche.colivin.room.dto.RoomResponse;
import java.util.List;
import java.util.UUID;

public interface RoomService {
    RoomResponse createRoom(UUID houseId, RoomRequest request);
    RoomResponse updateRoom(UUID houseId, UUID roomId, RoomRequest request);
    List<RoomResponse>  getRoomsByHouse(UUID houseId);
    // soft-delete
    void deleteRoom(UUID houseId, UUID roomId);

}
