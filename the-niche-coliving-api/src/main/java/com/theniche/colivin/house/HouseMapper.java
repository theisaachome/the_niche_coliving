package com.theniche.colivin.house;

import com.theniche.colivin.house.dto.HouseDetailResponse;
import com.theniche.colivin.house.dto.HouseRequest;
import com.theniche.colivin.house.dto.HouseResponse;
import org.springframework.stereotype.Component;

@Component
public class HouseMapper {

    House toEntity(HouseRequest request){
        return  new House()
                .setName(request.name())
                .setLocation(request.location())
                .setRemark(request.remark());
    }
    HouseResponse toResponse(House entity){
        return  new HouseResponse(
                entity.getId(),
                entity.getHouseCode(),
                entity.getName(),
                entity.getLocation(),
                entity.getRemark(),
                entity.getHouseStatus()
        );
    }
    HouseDetailResponse toDetailResponse(House entity){
        return new HouseDetailResponse(
                entity.getId(),
                entity.getHouseCode(),
                entity.getName(),
                entity.getLocation(),
                entity.getRemark(),
                entity.getHouseStatus(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate());
    }
}
