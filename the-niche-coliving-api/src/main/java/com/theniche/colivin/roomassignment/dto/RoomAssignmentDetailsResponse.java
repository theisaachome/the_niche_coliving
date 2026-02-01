package com.theniche.colivin.roomassignment.dto;
import com.theniche.colivin.roomassignment.AssignmentStatus;
import com.theniche.colivin.roomassignment.RoomAssignment;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RoomAssignmentDetailsResponse(
        UUID id,
        // Assignment
        AssignmentStatus assignmentStatus,
        LocalDate leaseStartDate,
        LocalDate leaseEndDate,
        BigDecimal depositAmount,
        RoomAssignment.DepositStatus depositStatus,
        String notes,

        // Tenant
        UUID tenantId,
        String tenantName,
        String tenantPhone,
        String tenantEmail,

        // Room
        UUID roomId,
        String roomNumber,
        Integer capacity,
//        BigDecimal monthlyRent,
        // House
        UUID houseId,
        String houseName){}
