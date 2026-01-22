package com.theniche.colivin.room.dto;

import com.theniche.colivin.room.RoomStatus;

import java.util.UUID;

public record RoomCreatedEvent(
        UUID houseId,
        RoomStatus roomStatus
) {}
