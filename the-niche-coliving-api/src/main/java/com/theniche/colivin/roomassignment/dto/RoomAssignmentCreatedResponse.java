package com.theniche.colivin.roomassignment.dto;
import java.util.UUID;

public record RoomAssignmentCreatedResponse(
        UUID id,
        String message){}
