package com.theniche.colivin.roomassignment;

import com.theniche.colivin.roomassignment.dto.RoomAssignmentOverviewResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentRequest;
import org.springframework.stereotype.Component;

@Component
public class RoomAssignmentMapper {
    RoomAssignment toEntity(RoomAssignmentRequest dto){
        var entity  = new RoomAssignment();
        return entity;

    }

    RoomAssignmentOverviewResponse toOverviewResponse(RoomAssignment entity){
        var tenantName = entity.getTenant().getFullName();
        return  new RoomAssignmentOverviewResponse(
                entity.getId(),
                tenantName,
                entity.getAssignmentStatus(),
                entity.getLeaseStartDate(),
                entity.getLeaseEndDate(),
                entity.getDepositAmount()
        );
    }
}
