package com.theniche.colivin.room.dto;

import com.theniche.colivin.room.RoomStatus;
import com.theniche.colivin.room.RoomType;

public record RoomRequest(
        RoomType roomType,
        int capacity,
        String roomNumber,
        RoomStatus roomStatus
) {

}
