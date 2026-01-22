package com.theniche.colivin.house.dto;

public record HouseCreateRequest(
        String name,
        String location,
        String remark
) {
}
