package com.theniche.colivin.service.impl;

import com.theniche.colivin.entity.Room;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.RoomRequestDto;
import com.theniche.colivin.repository.RoomRepository;
import com.theniche.colivin.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public ApiResponse addHouseRoom(RoomRequestDto dto) {
        return null;
    }

    @Override
    public List<RoomRequestDto> getHouseRoomsOfHouseById(UUID houseId) {
        List<Room> rooms = roomRepository.getHouseRoomsOfHouseById(houseId);
        return List.of();
    }
}
