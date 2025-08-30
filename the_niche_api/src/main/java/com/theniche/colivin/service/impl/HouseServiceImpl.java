package com.theniche.colivin.service.impl;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.HouseDto;
import com.theniche.colivin.payload.ResponseId;
import com.theniche.colivin.service.HouseService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class HouseServiceImpl implements HouseService {
    @Override
    public ApiResponse registerHouse(HouseDto dto) {
        var newHouseEntity = new ApiResponse("New House Registered",200,true,new ResponseId(UUID.randomUUID().toString()));
        return newHouseEntity;
    }
}
