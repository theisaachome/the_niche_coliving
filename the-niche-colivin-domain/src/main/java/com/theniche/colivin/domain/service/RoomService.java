package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.domain.exception.ResourceNotFoundException;
import com.theniche.colivin.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService extends BaseService<Room> {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository repository) {
        super(repository);
        this.roomRepository = repository;
    }
    @Override
    public Room update(UUID roomId, Room entity) {
        var existingRoom = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room","ID",roomId));

        existingRoom.setRoomNumber(entity.getRoomNumber());
        existingRoom.setCapacity(entity.getCapacity());
        existingRoom.setNotes(entity.getNotes());
       return  roomRepository.save(existingRoom);
    }


    public List<Room> getAllRoomByHouseId(UUID houseId){
        var roomLists = roomRepository.findByHouseId(houseId);
        return roomLists;
    }

    public Room findByRoomNumber(String roomNumber){
      return    roomRepository.findByRoomNumber(roomNumber)
                 .orElseThrow(()->new ResourceNotFoundException("Room","ID",null));
    }


    public List<Room> findAllRooms(){
        return roomRepository.findAll();
    }

    public List<Room> findAvailableRooms(){
        return null;
    }

}
