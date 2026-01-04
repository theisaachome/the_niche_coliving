package com.theniche.colivin.house.dto;

public class HouseSearchFilters {
    private String houseCode;
    private String houseName;

    public HouseSearchFilters() {}
    public HouseSearchFilters(String houseCode, String houseName) {
        this.houseCode = houseCode;
        this.houseName = houseName;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
