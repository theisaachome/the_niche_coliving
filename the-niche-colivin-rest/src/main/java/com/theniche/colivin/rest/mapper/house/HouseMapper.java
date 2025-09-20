package com.theniche.colivin.rest.mapper.house;

import com.theniche.colivin.domain.entity.House;
import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.house.HouseDetails;
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
                .setRemark(requestDto.remark());
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
                entity.getNotes(),
                entity.getHouseStatus(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
        );
    }

    public HouseDetails mapToDetails(House entity) {
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
                .collect(Collectors.toList());
        return new HouseDetails(   entity.getId(),
                entity.getName(),
                entity.getHouseCode(),
                entity.getNotes(),
                entity.getHouseStatus(),
                rooms,
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate());
    }
}
