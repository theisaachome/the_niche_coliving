package com.theniche.colivin.controller;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.ResponseId;
import com.theniche.colivin.payload.HouseDto;
import com.theniche.colivin.service.HouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("resource/v1/units")
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
        public ResponseEntity<ApiResponse> registerNewUnit(@Valid @RequestBody HouseDto dto){

            var result = this.houseService.registerHouse(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        @PutMapping("/{unit_id}")
        public ResponseEntity<ApiResponse> updateUnit(@Valid @PathVariable("unit_id") UUID uuid ,@RequestBody HouseDto dto){
            var result = new ApiResponse("Updated a Resource",200,true,new ResponseId(UUID.randomUUID().toString()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }


        // soft-delete
        @DeleteMapping("/{unit_id}")
        public ResponseEntity<ApiResponse> deleteUnit(@PathVariable("unit_id") UUID uuid){
            var result = new ApiResponse("deleted a Resource",200,true,new ResponseId(UUID.randomUUID().toString()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
}
