package com.theniche.colivin.domain.service;
import com.theniche.colivin.domain.entity.House;
import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.domain.exception.ResourceNotFoundException;
import com.theniche.colivin.domain.repository.HouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class HouseService extends BaseService<House> {
    private final HouseRepository houseRepository;


    public HouseService(HouseRepository houseRepository) {
        super(houseRepository);
        this.houseRepository = houseRepository;
    }
    @Override
    public House update(UUID id, House entity) {
        var existingHouse = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("House","ID",id));
        existingHouse.setName(entity.getName());
        existingHouse.setRemark(entity.getNotes());
        return repository.save(existingHouse);
    }

    @Transactional(readOnly = true)
    public House getHouseDetailsWithAllRooms(UUID id){
        return houseRepository.findHouseDetailsWithRooms(id).orElseThrow(()->new ResourceNotFoundException("House","ID",id));
    }


    public String addRoomToHouse(UUID houseId, Room room) {
        // look for house
        var house = repository.findById(houseId).orElseThrow(()->new ResourceNotFoundException("House","ID",houseId));
        house.addRoom(room);
        room.setHouse(house);
        repository.save(house);
        return String.valueOf(house.getId());
    }

    @Override
    public House deleteById(UUID id) {
        return super.deleteById(id);
    }
}
