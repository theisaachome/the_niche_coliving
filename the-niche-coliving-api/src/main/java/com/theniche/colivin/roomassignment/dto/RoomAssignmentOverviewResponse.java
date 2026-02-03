package com.theniche.colivin.roomassignment.dto;
import com.theniche.colivin.room.RoomType;
import com.theniche.colivin.roomassignment.AssignmentStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public record RoomAssignmentOverviewResponse(
        UUID id,
        String tenantName,
        AssignmentStatus assignmentStatus,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal depositAmount){}
