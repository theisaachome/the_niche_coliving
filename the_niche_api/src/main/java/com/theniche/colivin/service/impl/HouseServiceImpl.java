package com.theniche.colivin.service.impl;
import com.theniche.colivin.exception.ResourceNotFoundException;
import com.theniche.colivin.mapper.DataMapper;
import com.theniche.colivin.payload.*;
import com.theniche.colivin.repository.HouseRepository;
import com.theniche.colivin.repository.RoomRepository;
import com.theniche.colivin.service.HouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Service
public class HouseServiceImpl implements HouseService {

    private final DataMapper dataMapper;
    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;

    public HouseServiceImpl(DataMapper dataMapper, HouseRepository houseRepository, RoomRepository roomRepository) {
        this.dataMapper = dataMapper;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public HouseResponseDto findHouseById(UUID houseId) {
        var result = houseRepository.findById(houseId)
                .orElseThrow(()->new ResourceNotFoundException("House","ID",houseId));
        return dataMapper.mapToHouseDto(result);
    }

    @Transactional
    @Override
    public ApiResponse insertHouseWithRooms(HouseDto dto) {
        var entity = dataMapper.mapToHouseEntity(dto);
        var houseRooms =dataMapper.mapList(dto.rooms(),dataMapper::mapToHouseRoomEntity);
        houseRooms.forEach(room -> room.setHouse(entity));
        var savedEntity=houseRepository.save(entity);
        entity.setRooms(new HashSet<>(houseRooms));
        roomRepository.saveAll(houseRooms);
        return new ApiResponse("success",
                new ApiResponse.Data(
                        savedEntity.getId(),
                        savedEntity.getName(),
                        savedEntity.getCreatedDate(),
                        savedEntity.getCreatedBy()));
    }

    @Override
    public ApiResponse updateHouse(UUID houseId, UpdateHouseDto dto) {
        var houseEntity = houseRepository.findById(houseId)
                .orElseThrow(()->new ResourceNotFoundException("House","ID",houseId));
        houseEntity.setName(dto.name());
        houseEntity.setDescription(dto.description());
        houseEntity.setAddress(dto.address());
        houseEntity.setNotes(dto.notes());
        var updatedHouse = houseRepository.save(houseEntity);
        return new ApiResponse("success",
                new ApiResponse.Data(
                        updatedHouse.getId(),
                        updatedHouse.getName(),
                        updatedHouse.getUpdatedDate(),
                        updatedHouse.getUpdatedBy()));
    }

    @Override
    public ApiResponse deleteHouse(UUID houseId) {
        var foundHouse = houseRepository.findById(houseId)
                .orElseThrow(()->new ResourceNotFoundException("House","ID",houseId));
        houseRepository.delete(foundHouse);
        return new ApiResponse("success",
                new  ApiResponse.Data(foundHouse.getId(),foundHouse.getName(),foundHouse.getUpdatedDate(),foundHouse.getUpdatedBy()));
    }

    @Transactional
    @Override
    public ApiResponse addHouseRoom(UUID houseId, RoomRequestDto dto) {
        var house = houseRepository.findById(houseId)
                .orElseThrow(()->new ResourceNotFoundException("House","ID",houseId));
        house.addHouseRoom(dataMapper.mapToHouseRoomEntity(dto));
      var savedHouse=  houseRepository.save(house);
      return new ApiResponse("success",new ApiResponse.Data(savedHouse.getId(),savedHouse.getName(),savedHouse.getUpdatedDate(),savedHouse.getUpdatedBy()));
    }
}
