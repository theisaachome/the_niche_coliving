package com.theniche.colivin.controller;
import com.theniche.colivin.entity.House;
import com.theniche.colivin.payload.*;
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
               Register new Unit and Rooms
               /resource/v1/units
        */
        @PostMapping
        public ResponseEntity<ApiResponse> addNewHouseWithRooms(@Valid @RequestBody HouseDto dto){
            var result = this.houseService.insertHouseWithRooms(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }


        @PostMapping("/{houseId}/rooms")
        public ResponseEntity<ApiResponse> addRoomToHouse(@PathVariable("houseId")UUID houseId,@Valid @RequestBody HouseRoomDto dto){
           var result= houseService.addHouseRoom(houseId,dto);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }

        @GetMapping("/{houseId}")
        public ResponseEntity<HouseResponseDto>  getHouseById(@PathVariable UUID houseId){
            var result = this.houseService.findHouseById(houseId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        @PutMapping("/{houseId}")
        public ResponseEntity<ApiResponse> updateUnit(@Valid @PathVariable("houseId") UUID houseId ,@RequestBody UpdateHouseDto dto){
            var result =  this.houseService.updateHouse(houseId, dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }


        // soft-delete
        @DeleteMapping("/{houseId}")
        public ResponseEntity<ApiResponse> deleteUnit(@PathVariable("houseId") UUID uuid){
           var result= this.houseService.deleteHouse(uuid);
          return new ResponseEntity<>(result, HttpStatus.OK);
        }
}
