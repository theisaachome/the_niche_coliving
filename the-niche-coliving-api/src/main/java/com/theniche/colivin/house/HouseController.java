package com.theniche.colivin.house;
import com.theniche.colivin.house.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/houses")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity createHouse(@RequestBody HouseRequest request) {
        var newHouse= houseService.createHouse(request);
        return new ResponseEntity<>(newHouse, HttpStatus.CREATED);
    }
    @GetMapping("/overviews")
    public ResponseEntity<List<HouseOverviewResponse>> getHouseOverview(
            @RequestParam(value = "page",required = false,defaultValue = "0") int page,
            @RequestParam(value = "size",required = false,defaultValue = "20") int size){
        var result = this.houseService.getHouseOverview(page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<HouseResponse> searchHouse(@ModelAttribute HouseSearchFilters filters) {
        return ResponseEntity.ok(houseService.searchHouse(filters));
    }
    @GetMapping
    public List<HouseResponse> getHouse(){
        return houseService.getHouses();
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<HouseDetailResponse> getHouseDetails(@PathVariable("houseId") UUID houseId) {
        var houseDetails = houseService.getHouseDetail(houseId);
        return new ResponseEntity<>(houseDetails, HttpStatus.OK);
    }

    @PutMapping("/{houseId}")
    public ResponseEntity<HouseResponse> updateHouse(@PathVariable("houseId")UUID houseId, @RequestBody HouseRequest request) {
        var updatedHouse = houseService.updateHouse(houseId,request);
        return new ResponseEntity<>(updatedHouse, HttpStatus.OK);
    }

    @DeleteMapping("/{houseId}")
    public ResponseEntity<String> deleteHouse(@PathVariable("houseId")UUID houseId) {
        houseService.deleteHouse(houseId);
        return new ResponseEntity<>("Content Updated",HttpStatus.OK);
    }
}
