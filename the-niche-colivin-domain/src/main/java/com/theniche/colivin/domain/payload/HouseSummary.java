package com.theniche.colivin.domain.payload;
import java.time.LocalDateTime;
import java.util.UUID;

public record HouseSummary(
        UUID houseId,
        String houseName,
        String address,
        String notes,
        Integer totalRooms,
        String totalCapacity,
        String currentOccupancy,
        String occupancyRate,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
