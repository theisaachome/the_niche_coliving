package com.theniche.colivin.rest.mapper.room;
import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.rest.dto.room.RoomRequest;
import com.theniche.colivin.rest.dto.room.RoomResponse;
import com.theniche.colivin.rest.mapper.BaseMapper;
import org.springframework.stereotype.Component;



@Component
public class RoomMapper implements BaseMapper<Room, RoomRequest, RoomResponse> {
    @Override
    public Room requestToEntity(RoomRequest dto) {
        return new Room()
                .setRoomNumber(dto.roomNumber())
                .setNotes(dto.notes())
                .setCapacity(dto.capacity());
    }

    @Override
    public RoomResponse entityToResponse(Room entity) {
        return new RoomResponse(
                entity.getId(),
                entity.getRoomNumber(),
                entity.getRoomCode(),
                entity.getCapacity(),
                entity.getNotes(),
                entity.getRoomStatus(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
        );
    }
}
