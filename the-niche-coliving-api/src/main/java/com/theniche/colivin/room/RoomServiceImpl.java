package com.theniche.colivin.room;
import com.theniche.colivin.common.domain.EntityStatus;
import com.theniche.colivin.common.exception.ResourceNotFoundException;
import com.theniche.colivin.house.HouseRepository;
import com.theniche.colivin.room.dto.RoomCreatedEvent;
import com.theniche.colivin.room.dto.RoomRequest;
import com.theniche.colivin.room.dto.RoomResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final RoomMapper roomMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public RoomServiceImpl(RoomRepository roomRepository, HouseRepository houseRepository, RoomMapper roomMapper, ApplicationEventPublisher applicationEventPublisher) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.roomMapper = roomMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    @Override
    public RoomResponse createRoom(UUID houseId, RoomRequest request) {
        // find house by id
        var house = this.houseRepository.findById(houseId)
                .orElseThrow(()-> new ResourceNotFoundException("House","ID",houseId));
        var entityRoom = roomMapper.toEntity(request);
        entityRoom.setHouse(house);
        var savedEntity =  roomRepository.save(entityRoom);
        applicationEventPublisher.publishEvent(new RoomCreatedEvent(savedEntity.getHouse().getId(), savedEntity.getRoomStatus()));
        return this.roomMapper.toRoomResponse(savedEntity);
    }

    @Override
    public RoomResponse updateRoom(UUID houseId, UUID roomId, RoomRequest request) {
        // find room by id
        var room = this.roomRepository.findRoomByIdAndHouseId(roomId,houseId)
                .orElseThrow(()-> new ResourceNotFoundException("Room","ID",roomId));

        room.setRoomNumber(request.roomNumber())
                .setCapacity(request.capacity())
                .setRoomType(request.roomType())
                .setRemark(request.remark());
        return roomMapper.toRoomResponse(roomRepository.save(room));
    }

    @Override
    public List<RoomResponse> getRoomsByHouse(UUID houseId) {
        // Validate house exists (optional but recommended)
        if (!houseRepository.existsById(houseId)) {
            throw new ResourceNotFoundException("House", "ID", houseId);
        }
        return roomRepository.findRoomsByHouseId(houseId)
                .stream()
                .map(roomMapper::toRoomResponse)
                .toList();
    }

    @Override
    public void deleteRoom(UUID houseId, UUID roomId) {
        var room = this.roomRepository.findRoomByIdAndHouseId(houseId,roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room","ID",roomId));
        room.setDeleted(true);
    }
}
