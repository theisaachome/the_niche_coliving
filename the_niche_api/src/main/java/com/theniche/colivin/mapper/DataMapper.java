package com.theniche.colivin.mapper;

import com.theniche.colivin.entity.House;
import com.theniche.colivin.entity.HouseRoom;
import com.theniche.colivin.payload.HouseDto;
import com.theniche.colivin.payload.HouseRoomDto;
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

    public HouseRoom mapToHouseRoomEntity(HouseRoomDto dto){
        var houseRoomEntity = HouseRoom.builder()
                .roomNumber(dto.room_number())
                .capacity(dto.capacity())
                .notes(dto.notes())
                .build();
        return houseRoomEntity;
    }

    public <S,R>List<R> mapList(List<S> source, Function<? super S,? extends  R > mapper){
        Objects.requireNonNull(source);
        return source == null? List.of() : source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

}
