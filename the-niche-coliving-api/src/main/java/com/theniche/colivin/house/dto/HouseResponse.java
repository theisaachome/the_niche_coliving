package com.theniche.colivin.house.dto;
import com.theniche.colivin.house.HouseStatus;
import java.util.UUID;

public record HouseResponse(
        UUID id,
        String houseCode,
        String name,
        String location,
        String remark,
        HouseStatus status
) { }
