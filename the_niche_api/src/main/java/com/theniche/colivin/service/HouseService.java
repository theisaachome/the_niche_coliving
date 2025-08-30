package com.theniche.colivin.service;

import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.HouseDto;

public interface HouseService {
    ApiResponse registerHouse(HouseDto dto);
}
