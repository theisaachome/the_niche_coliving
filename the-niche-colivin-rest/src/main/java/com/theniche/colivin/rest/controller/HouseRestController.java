package com.theniche.colivin.rest.controller;
import com.theniche.colivin.domain.entity.House;
import com.theniche.colivin.domain.service.HouseService;
import com.theniche.colivin.domain.service.RoomService;
import com.theniche.colivin.rest.dto.house.HouseDetails;
import com.theniche.colivin.rest.dto.house.HouseRequest;
import com.theniche.colivin.rest.dto.house.HouseResponse;
import com.theniche.colivin.rest.dto.room.RoomRequest;
import com.theniche.colivin.rest.dto.room.RoomResponse;
import com.theniche.colivin.rest.mapper.house.HouseMapper;
import com.theniche.colivin.rest.mapper.room.RoomMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/houses")
public class HouseRestController extends BaseController<House, HouseRequest, HouseResponse>{
    private final HouseService houseService;
    private final HouseMapper houseMapper;
    private final RoomMapper roomMapper;
    private final RoomService roomService;

    public HouseRestController(HouseService houseService, HouseMapper houseMapper, RoomMapper roomMapper, RoomService roomService) {
        super(houseService,houseMapper);
        this.houseService = houseService;
        this.houseMapper = houseMapper;
        this.roomMapper = roomMapper;
        this.roomService = roomService;
    }

    @PutMapping("/{id}")
    @Override
    protected  ResponseEntity<HouseResponse> update(@PathVariable("id") UUID id,@RequestBody HouseRequest requestDto) {
        var entity = houseMapper.requestToEntity(requestDto);
        var result= houseService.update(id,entity);
        return new ResponseEntity<>(houseMapper.entityToResponse(result), HttpStatus.OK);
    }

    @GetMapping("/{houseId}/details")
    ResponseEntity<HouseDetails> getHouseDetails(@PathVariable("houseId") UUID id){
        var entity = houseService.getHouseDetailsWithAllRooms(id);
        var dto = houseMapper.mapToDetails(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/{houseId}/rooms")
    ResponseEntity<String> addRoom(@PathVariable("houseId")UUID houseId,@Valid @RequestBody RoomRequest roomRequestDto) {
        var roomEntity = roomMapper.requestToEntity(roomRequestDto);
        var result =houseService.addRoomToHouse(houseId,roomEntity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //View All rooms from UI ( get all rooms by house-id )
    @GetMapping("/{houseId}/rooms")
    ResponseEntity<List<RoomResponse>> viewAllRoomsUnderAHouse(@PathVariable("houseId") UUID houseId) {
        var result = roomService.getAllRoomByHouseId(houseId);
        var dtoList = roomMapper.mapList(result,roomMapper::entityToResponse);
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }


}
