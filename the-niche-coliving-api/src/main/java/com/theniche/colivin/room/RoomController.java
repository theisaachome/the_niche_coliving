package com.theniche.colivin.room;

import com.theniche.colivin.room.dto.RoomRequest;
import com.theniche.colivin.room.dto.RoomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/houses/{houseId}/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(
            @PathVariable UUID houseId,
            @RequestBody RoomRequest request) {

        var room = roomService.createRoom(houseId, request);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    public List<RoomResponse> getRooms(@PathVariable UUID houseId) {
        return roomService.getRoomsByHouse(houseId);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable UUID houseId,
            @PathVariable UUID roomId,
            @RequestBody RoomRequest request) {

        var room = roomService.updateRoom(houseId, roomId, request);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(
            @PathVariable UUID houseId,
            @PathVariable UUID roomId) {

        roomService.deleteRoom(houseId, roomId);
        return ResponseEntity.noContent().build();
    }
}
