package com.theniche.colivin.house;

import com.theniche.colivin.house.dto.*;

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
    HouseOverviewResponse  getHouseOverview();
}
