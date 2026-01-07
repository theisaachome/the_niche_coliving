package com.theniche.colivin.room.dto;

import com.theniche.colivin.room.RoomType;

public record RoomRequest(
        String roomNumber,
        RoomType roomType,
        int capacity,
        String remark
) {}
