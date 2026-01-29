package com.theniche.colivin.roomassignment;

import com.theniche.colivin.roomassignment.dto.RoomAssignmentCreatedResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentRequest;

import java.util.UUID;

public interface RoomAssignmentService {
    RoomAssignmentCreatedResponse  create(RoomAssignmentRequest request);
    RoomAssignment findById(UUID id);
    RoomAssignment update(UUID roomAssignmentId, RoomAssignment roomAssignment);
}
