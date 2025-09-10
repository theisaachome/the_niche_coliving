package com.theniche.colivin.rest.controller;

import com.theniche.colivin.domain.entity.House;
import com.theniche.colivin.domain.service.HouseService;
import com.theniche.colivin.rest.dto.house.HouseRequest;
import com.theniche.colivin.rest.dto.house.HouseResponse;
import com.theniche.colivin.rest.mapper.house.HouseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/houses")
public class HouseRestController extends BaseController<House, HouseRequest, HouseResponse>{
    private final HouseService houseService;
    private final HouseMapper houseMapper;
    public HouseRestController(HouseService houseService,HouseMapper houseMapper) {
        super(houseService,houseMapper);
        this.houseService = houseService;
        this.houseMapper = houseMapper;
    }

    @Override
    protected <D> ResponseEntity<D> update(UUID id, HouseRequest requestDto) {
        return null;
    }
}
