package com.theniche.colivin.service.impl;
import com.theniche.colivin.entity.House;
import com.theniche.colivin.entity.HouseRoom;
import com.theniche.colivin.exception.ResourceNotFoundException;
import com.theniche.colivin.mapper.DataMapper;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.HouseDto;
import com.theniche.colivin.payload.HouseResponseDto;
import com.theniche.colivin.payload.UpdateHouseDto;
import com.theniche.colivin.repository.HouseRepository;
import com.theniche.colivin.repository.HouseRoomRepository;
import com.theniche.colivin.service.HouseService;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class HouseServiceImpl implements HouseService {

    private final DataMapper dataMapper;
    private final HouseRepository houseRepository;
    private final HouseRoomRepository houseRoomRepository;

    public HouseServiceImpl(DataMapper dataMapper, HouseRepository houseRepository, HouseRoomRepository houseRoomRepository) {
        this.dataMapper = dataMapper;
        this.houseRepository = houseRepository;
        this.houseRoomRepository = houseRoomRepository;
    }

    @Override
    public HouseResponseDto findHouseById(UUID houseId) {
        var result = houseRepository.findById(houseId)
                .orElseThrow(()->new ResourceNotFoundException("House","ID",houseId));
        return dataMapper.mapToHouseDto(result);
    }

    @Override
    public ApiResponse registerHouse(HouseDto dto) {
        var entity = dataMapper.mapToHouseEntity(dto);

        var houseRooms =dataMapper.mapList(dto.rooms(),dataMapper::mapToHouseRoomEntity);
        houseRooms.forEach(room -> room.setHouse(entity));

        var savedEntity=houseRepository.save(entity);
        entity.setRooms(new HashSet<>(houseRooms));


        houseRoomRepository.saveAll(houseRooms);


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
}
