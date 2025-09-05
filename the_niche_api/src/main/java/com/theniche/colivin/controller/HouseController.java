package com.theniche.colivin.controller;
import com.theniche.colivin.payload.*;
import com.theniche.colivin.payload.house.HouseDto;
import com.theniche.colivin.payload.house.HouseResponseDto;
import com.theniche.colivin.payload.house.UpdateHouseDto;
import com.theniche.colivin.payload.room.RoomRequestDto;
import com.theniche.colivin.service.HouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("resource/v1/houses")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }
        /*
          POST /houses → Create a house
         */
        @PostMapping
        public ResponseEntity<ApiResponse<ResponseData>> addNewHouseWithRooms(@Valid @RequestBody HouseDto dto){
            var result = this.houseService.insertHouseWithRooms(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        //  POST /houses/{houseId}/rooms → Add room to a house
        @PostMapping("/{houseId}/rooms")
        public ResponseEntity<ApiResponse<ResponseData>> addRoomToHouse(@PathVariable("houseId")UUID houseId,@Valid @RequestBody RoomRequestDto dto){
           var result= houseService.addHouseRoom(houseId,dto);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }

        // GET /houses/{houseId} → Get house details
        @GetMapping("/{houseId}")
        public ResponseEntity<ApiResponse<HouseResponseDto>>  getHouseById(@PathVariable UUID houseId){
            var result = this.houseService.findHouseById(houseId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        @PutMapping("/{houseId}")
        public ResponseEntity<ApiResponse<ResponseData>> updateUnit(@Valid @PathVariable("houseId") UUID houseId ,@RequestBody UpdateHouseDto dto){
            var result =  this.houseService.updateHouse(houseId, dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        // soft-delete
        @DeleteMapping("/{houseId}")
        public ResponseEntity<ApiResponse<ResponseData>> deleteUnit(@PathVariable("houseId") UUID uuid){
           var result= this.houseService.softDeleteHouse(uuid);
          return new ResponseEntity<ApiResponse<ResponseData>>(result, HttpStatus.OK);
        }
        // GET /houses/{houseId}/rooms → List rooms in a house
}
