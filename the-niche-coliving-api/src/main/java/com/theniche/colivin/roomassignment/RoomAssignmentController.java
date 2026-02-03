package com.theniche.colivin.roomassignment;
import com.theniche.colivin.common.BaseController;
import com.theniche.colivin.common.payload.PagedApiResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentDetailsResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentListResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentOverviewResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room-assignments")
public class RoomAssignmentController extends BaseController {

    private final RoomAssignmentService roomAssignmentService;

    public RoomAssignmentController(RoomAssignmentService roomAssignmentService) {
        this.roomAssignmentService = roomAssignmentService;
    }

    // POST   /api/v1/room-assignments
    @PostMapping
    public ResponseEntity<String> createRoomAssignment(@RequestBody RoomAssignmentRequest data) {
        logger.info("Creating room assignment {}", data);
        this.roomAssignmentService.create(data);
        return  ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> updateRoomAssignment(@RequestBody RoomAssignmentRequest data) {
        logger.info("Updating room assignment {}", data);
        return  ResponseEntity.ok().build();
    }
    // DELETE /api/v1/room-assignments/{assignmentId}
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<String> deleteRoomAssignment(@PathVariable UUID assignmentId) {
        logger.info("Deleting room assignment {}", assignmentId);
        return   ResponseEntity.ok().build();
    }

    @GetMapping
    public PagedApiResponse<RoomAssignmentListResponse> listRoomAssignments(
            @RequestParam(required = false) Long houseId,
            @RequestParam(required = false) AssignmentStatus status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endingBefore,
            Pageable pageable
    ) {
        return roomAssignmentService.listAssignments(
                houseId, status, endingBefore, pageable
        );
    }

    // GET    /api/v1/room-assignments/rooms/{roomId}
    @GetMapping("/{assignmentId}")
    public ResponseEntity<String> getRoomAssignment(@PathVariable UUID assignmentId) {
        logger.info("Getting room assignment {}", assignmentId);
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/{roomAssignmentId}/details")
    public ResponseEntity<RoomAssignmentDetailsResponse> getRoomAssignmentsDetails(@PathVariable UUID roomAssignmentId) {
        return new ResponseEntity<>(roomAssignmentService.findDetails(roomAssignmentId), HttpStatus.OK);
    }
    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<List<RoomAssignmentOverviewResponse>> getRoomAssignmentByRoom(
            @PathVariable UUID roomId,
            @RequestParam(name = "assignmentStatus", required = false,defaultValue = "ACTIVE") AssignmentStatus assignmentStatus) {
        logger.info("Getting room assignments by Room{}", roomId);
        var results = this.roomAssignmentService.findRoomAssignmentsByRoomId(roomId, assignmentStatus);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
