package com.theniche.colivin.rest.mapper.house;

import com.theniche.colivin.domain.entity.House;
import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.house.HouseRequest;
import com.theniche.colivin.rest.dto.house.HouseResponse;
import com.theniche.colivin.rest.dto.room.RoomResponse;
import com.theniche.colivin.rest.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class HouseMapper implements BaseMapper<House, HouseRequest, HouseResponse> {

    @Override
    public House requestToEntity(HouseRequest requestDto) {
        var house= new House()
                .setName(requestDto.name())
                .setDescription(requestDto.description())
                .setAddress(requestDto.address())
                .setNotes(requestDto.notes());
        // map if room present
        if(requestDto.rooms() != null && !requestDto.rooms().isEmpty()) {
            var rooms =  requestDto.rooms()
                    .stream().map(r -> new Room()
                            .setRoomNumber(r.roomNumber())
                            .setCapacity(r.capacity())
                            .setNotes(r.notes())
                            .setHouse(house)).collect(Collectors.toSet());
            rooms.forEach(house::addRoom);
        }

        return house;
    }

    @Override
    public HouseResponse entityToResponse(House entity) {
        var rooms = entity.getRooms()
                .stream()
                .map(room->new RoomResponse(
                        room.getId(),
                        room.getRoomNumber(),
                        room.getRoomCode(),
                        room.getCapacity(),
                        room.getNotes(),
                        room.getRoomStatus(),
                        room.getCreatedBy(),
                        room.getUpdatedBy(),
                        room.getCreatedDate(),
                        room.getUpdatedDate()
                        ))
                .collect(Collectors.toSet());

        return new HouseResponse(
                entity.getId(),
                entity.getName(),
                entity.getHouseCode(),
                entity.getDescription(),
                entity.getAddress(),
                entity.getNotes(),
                entity.getHouseStatus(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate(),
                rooms
        );
    }
}
