package com.theniche.colivin.roomassignment.dto;

import com.theniche.colivin.roomassignment.RoomAssignment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RoomAssignmentRequest(
        UUID houseId,
        UUID roomId,
        UUID tenantId,
        LocalDate leaseStartDate,
        LocalDate leaseEndDate,
        BigDecimal depositAmount,
        RoomAssignment.DepositStatus depositStatus,
        String notes) {
}
