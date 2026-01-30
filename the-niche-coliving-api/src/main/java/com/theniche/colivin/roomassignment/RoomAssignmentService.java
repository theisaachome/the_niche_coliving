package com.theniche.colivin.roomassignment;

import com.theniche.colivin.roomassignment.dto.RoomAssignmentCreatedResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentOverviewResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentRequest;

import java.util.List;
import java.util.UUID;

public interface RoomAssignmentService {
    RoomAssignmentCreatedResponse  create(RoomAssignmentRequest request);
    RoomAssignment findById(UUID id);
    RoomAssignment update(UUID roomAssignmentId, RoomAssignment roomAssignment);
    List<RoomAssignmentOverviewResponse> findRoomAssignmentsByRoomId(UUID roomId, AssignmentStatus assignmentStatus);
}
