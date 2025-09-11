package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.common.BaseRepository;
import com.theniche.colivin.domain.common.BaseService;
import com.theniche.colivin.domain.entity.House;
import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.domain.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HouseService extends BaseService<House> {

    public HouseService(BaseRepository<House> repository) {
        super(repository);
    }

    @Override
    public House save(House entity) {
        if(entity.getRooms() != null){
            entity.getRooms().forEach(entity::addRoom);
        }
        return super.save(entity);
    }

    @Override
    public House update(UUID id, House entity) {
        var existingHouse = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("House","ID",id));
        existingHouse.setName(entity.getName());
        existingHouse.setAddress(entity.getAddress());
        existingHouse.setDescription(entity.getDescription());
        existingHouse.setNotes(entity.getNotes());
        return repository.save(existingHouse);
    }


    public String addRoomToHouse(UUID houseId, Room room) {
        // look for house
        var house = repository.findById(houseId).orElseThrow(()->new ResourceNotFoundException("House","ID",houseId));
        house.addRoom(room);
        room.setHouse(house);
        repository.save(house);
        return String.valueOf(house.getId());
    }

}
