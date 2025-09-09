package com.theniche.colivin.rest.mapper;
import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.room.RoomRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RoomMapper implements BaseMapper<Room, RoomRequestDto, ApiResponse>{
    @Override
    public Room requestToEntity(RoomRequestDto requestDto) {
        return Room.builder()
                .roomNumber(requestDto.roomNumber())
                .capacity(requestDto.capacity())
                .notes(requestDto.notes())
                .build();
    }

    @Override
    public ApiResponse entityToResponse(Room entity) {
        return new ApiResponse("","","");
    }

    @Override
    public List<ApiResponse> entitiesToResponses(List<Room> entities) {
        return List.of();
    }
}
