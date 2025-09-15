package com.theniche.colivin.rest.controller;
import com.theniche.colivin.domain.service.AddressService;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.address.AddressDto;
import com.theniche.colivin.rest.mapper.address.AddressMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressRestController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public AddressRestController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }
    @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable("addressId")UUID id, @RequestBody AddressDto request){
        var entity =  addressMapper.requestToEntity(request);
        var saveEntity = addressService.update(id,entity);
        return ResponseEntity.ok(new ApiResponse("success","Address updated successfully",saveEntity.getId()));
    }
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId")UUID id){
        var result = addressService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("success","Address deleted successfully",result.getId()));
    }
}
