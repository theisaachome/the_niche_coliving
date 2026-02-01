package com.theniche.colivin.room;

import com.theniche.colivin.common.domain.BaseEntity;
import com.theniche.colivin.house.House;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_price_policies",
uniqueConstraints =@UniqueConstraint(columnNames = {"id","house","roomType","effective_from"}))
public class RoomPricePolicy extends BaseEntity {


    public enum RoomPricePolicyStatus{
        ACTIVE,
        INACTIVE,
        DELETE
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private House house;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private BigDecimal suggestedRent;
    private int DepositMultiplier;
    @Column(nullable = false,name = "effective_from")
    private LocalDateTime effectiveFrom;

    @Column(nullable = false,name = "effective_to")
    private LocalDateTime effectiveTo;
    @Enumerated(EnumType.STRING)
    private RoomPricePolicyStatus roomPricePolicyStatus;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getSuggestedRent() {
        return suggestedRent;
    }

    public void setSuggestedRent(BigDecimal suggestedRent) {
        this.suggestedRent = suggestedRent;
    }

    public int getDepositMultiplier() {
        return DepositMultiplier;
    }

    public void setDepositMultiplier(int depositMultiplier) {
        DepositMultiplier = depositMultiplier;
    }

    public LocalDateTime getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDateTime effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDateTime getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(LocalDateTime effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    public RoomPricePolicyStatus getRoomPricePolicyStatus() {
        return roomPricePolicyStatus;
    }

    public void setRoomPricePolicyStatus(RoomPricePolicyStatus roomPricePolicyStatus) {
        this.roomPricePolicyStatus = roomPricePolicyStatus;
    }
}
