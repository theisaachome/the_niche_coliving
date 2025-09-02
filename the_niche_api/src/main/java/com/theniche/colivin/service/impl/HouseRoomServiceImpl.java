package com.theniche.colivin.service.impl;

import com.theniche.colivin.entity.HouseRoom;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.HouseRoomDto;
import com.theniche.colivin.repository.HouseRoomRepository;
import com.theniche.colivin.service.HouseRoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HouseRoomServiceImpl implements HouseRoomService {
    private final HouseRoomRepository houseRoomRepository;

    public HouseRoomServiceImpl(HouseRoomRepository houseRoomRepository) {
        this.houseRoomRepository = houseRoomRepository;
    }

    @Override
    public ApiResponse addHouseRoom(HouseRoomDto dto) {
        return null;
    }

    @Override
    public List<HouseRoomDto> getHouseRoomsOfHouseById(UUID houseId) {
        List<HouseRoom> houseRooms = houseRoomRepository.getHouseRoomsOfHouseById(houseId);
        return List.of();
    }
}
