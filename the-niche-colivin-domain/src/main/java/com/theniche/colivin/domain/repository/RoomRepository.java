package com.theniche.colivin.domain.repository;
import com.theniche.colivin.domain.common.BaseRepository;
import com.theniche.colivin.domain.entity.Room;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends BaseRepository<Room> {

    List<Room> findByHouseId(UUID houseId);
    Optional<Room> findByRoomNumber(String roomNumber);
}
