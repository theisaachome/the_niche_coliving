package com.theniche.colivin.roomassignment;

import com.theniche.colivin.roomassignment.dto.RoomAssignmentRequest;
import org.springframework.stereotype.Component;

@Component
public class RoomAssignmentMapper {
    RoomAssignment toEntity(RoomAssignmentRequest dto){
        var entity  = new RoomAssignment();
        return entity;

    }
}
