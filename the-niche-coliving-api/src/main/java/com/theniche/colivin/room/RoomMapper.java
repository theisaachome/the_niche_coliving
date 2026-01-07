package com.theniche.colivin.room;

import com.theniche.colivin.room.dto.RoomRequest;
import com.theniche.colivin.room.dto.RoomResponse;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    Room toEntity (RoomRequest request){
        Room room = new Room()
                .setRoomType(request.roomType())
                .setRoomNumber(request.roomNumber())
                .setCapacity(request.capacity())
                .setRemark(request.remark());
        return room;
    }
    RoomResponse toRoomResponse (Room room){
        return  new RoomResponse(room.getId(),
                room.getRoomNumber(),
                room.getRoomType(),
                room.getRoomCode(),
                room.getCapacity(),
                room.getRemark(),
                room.getRoomStatus());
    }

}
