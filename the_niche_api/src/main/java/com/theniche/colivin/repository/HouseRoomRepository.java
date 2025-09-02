package com.theniche.colivin.repository;
import com.theniche.colivin.entity.HouseRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface HouseRoomRepository extends JpaRepository<HouseRoom, Long> {

    @Transactional
    @Query("SELECT r FROM HouseRoom  r WHERE r.house.id = :houseId")
    List<HouseRoom> getHouseRoomsOfHouseById(UUID houseId);
}
