package com.theniche.colivin.controller;

import com.theniche.colivin.entity.Address;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.address.AddressDto;
import com.theniche.colivin.service.AddressService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    // add address

    // update address
    @PutMapping(value = "/{addressId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable UUID addressId, @RequestBody AddressDto dto) {
        return  new ResponseEntity<>(addressService.updateAddress(addressId,dto), HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<AddressDto> getAddress(UUID uuid) {
        return null;
    }

}
