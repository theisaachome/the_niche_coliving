package com.theniche.colivin.house;

import com.theniche.colivin.house.dto.HouseDetailResponse;
import com.theniche.colivin.house.dto.HouseRequest;
import com.theniche.colivin.house.dto.HouseResponse;
import com.theniche.colivin.house.dto.HouseSearchFilters;

import java.util.List;
import java.util.UUID;

public interface HouseService {
    HouseResponse createHouse(HouseRequest request);
    HouseResponse updateHouse(UUID id, HouseRequest request);
    HouseResponse getHouse(UUID id);
    HouseDetailResponse getHouseDetail(UUID id);
    void deleteHouse(UUID id);
    List<HouseResponse> getHouses();
    HouseResponse searchHouse(HouseSearchFilters filters);
}
