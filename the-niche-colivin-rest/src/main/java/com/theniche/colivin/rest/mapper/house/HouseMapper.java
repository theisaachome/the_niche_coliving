package com.theniche.colivin.rest.mapper.house;

import com.theniche.colivin.domain.entity.House;
import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.house.HouseRequest;
import com.theniche.colivin.rest.dto.house.HouseResponse;
import com.theniche.colivin.rest.dto.room.RoomResponse;
import com.theniche.colivin.rest.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HouseMapper implements BaseMapper<House, HouseRequest, HouseResponse> {

    @Override
    public House requestToEntity(HouseRequest requestDto) {
        var house= House.builder()
                .name(requestDto.name())
                .description(requestDto.description())
                .address(requestDto.address())
                .notes(requestDto.notes())
                .build();
        // map if room present
        if(requestDto.rooms() != null && !requestDto.rooms().isEmpty()) {
            var rooms =  requestDto.rooms()
                    .stream().map(r -> Room.builder()
                            .roomNumber(r.roomNumber())
                            .capacity(r.capacity())
                            .notes(r.notes())
                            .house(house)
                    .build()).collect(Collectors.toSet());
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
                        room.getCapacity(),
                        room.getNotes(),
                        room.getCreatedBy(),
                        room.getUpdatedBy(),
                        room.getCreatedDate(),
                        room.getUpdatedDate()
                        ))
                .collect(Collectors.toSet());

        return new HouseResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getAddress(),
                entity.getNotes(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate(),
                rooms
        );
    }
}
