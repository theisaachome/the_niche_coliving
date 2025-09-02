package com.theniche.colivin.repository;
import com.theniche.colivin.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Transactional
    @Query("SELECT r FROM Room  r WHERE r.house.id = :houseId")
    List<Room> getHouseRoomsOfHouseById(UUID houseId);
}
