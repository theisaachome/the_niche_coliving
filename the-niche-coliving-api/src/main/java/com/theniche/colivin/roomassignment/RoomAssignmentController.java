package com.theniche.colivin.roomassignment;

import com.theniche.colivin.common.BaseController;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room-assignments")
public class RoomAssignmentController extends BaseController {
    // POST   /api/v1/room-assignments
    @PostMapping
    public ResponseEntity<String> createRoomAssignment(@RequestBody RoomAssignmentRequest data) {
        logger.info("Creating room assignment {}", data);
        return  ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> updateRoomAssignment(@RequestBody RoomAssignmentRequest data) {
        logger.info("Updating room assignment {}", data);
        return  ResponseEntity.ok().build();
    }
    // DELETE /api/v1/room-assignments/{assignmentId}
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<String> deleteRoomAssignment(@PathVariable String assignmentId) {
        logger.info("Deleting room assignment {}", assignmentId);
        return   ResponseEntity.ok().build();
    }
    // GET    /api/v1/room-assignments/rooms/{roomId}
    @GetMapping("/{assignmentId}")
    public ResponseEntity<String> getRoomAssignment(@PathVariable String assignmentId) {
        logger.info("Getting room assignment {}", assignmentId);
        return  ResponseEntity.ok().build();
    }
}
