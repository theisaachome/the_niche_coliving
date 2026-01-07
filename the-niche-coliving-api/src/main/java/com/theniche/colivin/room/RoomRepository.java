package com.theniche.colivin.room;

import com.theniche.colivin.common.repository.BaseRepository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends BaseRepository<Room> {
    List<Room> findRoomsByHouseId(UUID houseId);
    List<Room> findRoomsByRoomNumber(String roomNumber);
    Optional<Room> findRoomByIdAndHouseId(UUID roomId, UUID houseId);
}
