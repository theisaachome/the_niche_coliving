package com.theniche.colivin.rest.controller;
import com.theniche.colivin.domain.entity.Room;
import com.theniche.colivin.domain.service.RoomService;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.room.RoomRequest;
import com.theniche.colivin.rest.dto.room.RoomResponse;
import com.theniche.colivin.rest.mapper.room.RoomMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/rooms")
public class RoomRestController extends BaseController<Room, RoomRequest, RoomResponse> {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    public RoomRestController(RoomService roomService, RoomMapper roomMapper) {
        super(roomService, roomMapper);
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }

    @PutMapping("/{roomId}")
    @Override
    protected  ResponseEntity<ApiResponse<RoomResponse>> update(@PathVariable("roomId") UUID id,@RequestBody RoomRequest requestDto) {
        var savedRoomEntity = roomService.update(id,roomMapper.requestToEntity(requestDto));
        var dtoRoom = roomMapper.entityToResponse(savedRoomEntity);
        return new ResponseEntity<>(new ApiResponse<>("success","Room updated",dtoRoom), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        var roomList = roomService.findAll();
        var dtoList = roomMapper.mapList(roomList,roomMapper::entityToResponse);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
