package com.theniche.colivin.roomassignment.dto;
import com.theniche.colivin.room.RoomType;
import com.theniche.colivin.roomassignment.AssignmentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RoomAssignmentListResponse(UUID assignmentId, UUID houseId, String houseName, UUID roomId,
                                         String roomCode, RoomType roomType, UUID tenantId, String tenantName,
                                         LocalDate startDate, LocalDate endDate, AssignmentStatus status,
                                         BigDecimal monthlyRent) {

    //    private final Payment.PaymentStatus paymentStatus;
//    private final String paymentStatus;


    // getters only

}
