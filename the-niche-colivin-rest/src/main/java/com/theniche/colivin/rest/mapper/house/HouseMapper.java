package com.theniche.colivin.rest.mapper.house;

import com.theniche.colivin.domain.entity.House;
import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.house.HouseRequest;
import com.theniche.colivin.rest.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HouseMapper implements BaseMapper<House, HouseRequest, ApiResponse> {

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
    public ApiResponse entityToResponse(House entity) {
        return new ApiResponse("success","New Entity Added","");
    }

    @Override
    public List<ApiResponse> entitiesToResponses(List<House> entities) {
        return List.of();
    }
}
