package com.theniche.colivin.roomassignment;

import com.theniche.colivin.common.payload.PagedApiResponse;
import com.theniche.colivin.roomassignment.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RoomAssignmentService {
    RoomAssignmentCreatedResponse  create(RoomAssignmentRequest request);
    RoomAssignment findById(UUID id);
    RoomAssignmentDetailsResponse findDetails(UUID id);
    RoomAssignment update(UUID roomAssignmentId, RoomAssignment roomAssignment);
    List<RoomAssignmentOverviewResponse> findRoomAssignmentsByRoomId(UUID roomId, AssignmentStatus assignmentStatus);
    PagedApiResponse<RoomAssignmentListResponse> listAssignments(
            Long houseId,
            AssignmentStatus status,
            LocalDate endingBefore,
            Pageable pageable);
}
