package com.theniche.colivin.service.impl;

import com.theniche.colivin.entity.Room;
import com.theniche.colivin.exception.ResourceNotFoundException;
import com.theniche.colivin.mapper.DataMapper;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.room.RoomRequestDto;
import com.theniche.colivin.payload.room.RoomResponseDto;
import com.theniche.colivin.repository.HouseRepository;
import com.theniche.colivin.repository.RoomRepository;
import com.theniche.colivin.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final DataMapper dataMapper;

    public RoomServiceImpl(RoomRepository roomRepository, HouseRepository houseRepository, DataMapper dataMapper) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.dataMapper = dataMapper;
    }

    @Override
    public ApiResponse addRoomToHouse(RoomRequestDto dto) {
        ///  todo
        return null;
    }

    @Override
    public List<RoomRequestDto> getRoomsOfHouseById(UUID houseId) {
        List<Room> rooms = roomRepository.getHouseRoomsOfHouseById(houseId);
        return List.of();
    }

    @Override
    public ApiResponse<RoomResponseDto> getRoomDetails(UUID roomId) {
        var roomDetails = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room","ID",roomId));
        return new ApiResponse<>("success",
                "getRoomDetails retrieved successfully",
                dataMapper.mapToHouseRoomDto(roomDetails));
    }

    @Override
    public ApiResponse<ResponseData> updateRoom(UUID roomId, RoomRequestDto dto) {
        var roomDetails = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room","ID",roomId));
        roomDetails.setRoomNumber(dto.room_number());
        roomDetails.setCapacity(dto.capacity());
        roomDetails.setNotes(dto.notes());
        return new ApiResponse<>("success",
                "getRoomDetails retrieved successfully",
                new ResponseData(roomDetails.getId(),
                roomDetails.getRoomNumber(),roomDetails.getUpdatedDate(), roomDetails.getUpdatedBy()));
    }

    @Override
    public ApiResponse<ResponseData> deleteRoom(UUID roomId) {
        var existingRoom = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room","ID",roomId));
        existingRoom.setDeleted(true);
        roomRepository.save(existingRoom);
        return new ApiResponse<>("success",
                "deleteRoom operation successfully",
        new ResponseData(existingRoom.getId(),
                existingRoom.getRoomNumber(),
                existingRoom.getUpdatedDate(),
                existingRoom.getUpdatedBy()));
    }

    @Override
    public List<RoomResponseDto> searchRooms() {
        return List.of();
    }
}
