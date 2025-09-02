package com.theniche.colivin.mapper;

import com.theniche.colivin.entity.House;
import com.theniche.colivin.entity.Room;
import com.theniche.colivin.payload.HouseDto;
import com.theniche.colivin.payload.RoomRequestDto;
import com.theniche.colivin.payload.HouseResponseDto;
import com.theniche.colivin.payload.RoomResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DataMapper  {


    public House mapToHouseEntity(HouseDto dto){
        var houseEntity = House.builder()
                .name(dto.name())
                .address(dto.address())
                .description(dto.description())
                .notes(dto.notes())
                .build();
        return houseEntity;
    }

    public Room mapToHouseRoomEntity(RoomRequestDto dto){
        var houseRoomEntity = Room.builder()
                .roomNumber(dto.room_number())
                .capacity(dto.capacity())
                .notes(dto.notes())
                .build();
        return houseRoomEntity;
    }
    public RoomResponseDto mapToHouseRoomDto(Room entity){
        return  new RoomResponseDto(
                entity.getId(),
                entity.getRoomNumber(),
                entity.getCapacity(),
                entity.getNotes(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
        );
    }

    public HouseResponseDto mapToHouseDto(House entity){
        var houseResponseDto = new HouseResponseDto(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getAddress(),
                entity.getNotes(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate(),
                entity.getRooms().stream().map(r->this.mapToHouseRoomDto(r))
                        .collect(Collectors.toSet()));
        return houseResponseDto;
    }

    public <S,R>List<R> mapList(List<S> source, Function<? super S,? extends  R > mapper){
        Objects.requireNonNull(source);
        return source == null? List.of() : source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

}
