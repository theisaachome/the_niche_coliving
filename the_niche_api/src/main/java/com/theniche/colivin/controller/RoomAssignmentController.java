package com.theniche.colivin.controller;

import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.tenant.TenantRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assignments")
public class RoomAssignmentController {

    /*
        POST   /api/v1/room-assignments        # assign tenant to room
        DELETE /api/v1/room-assignments/{id}   # unassign tenant (checkout)
        GET    /api/v1/room-assignments        # list all assignments
        GET    /api/v1/room-assignments/{id}   # get specific assignment

        POST /api/v1/room-assignments
        {
          "roomId": 100,
          "tenantId": 200
        }

     */


//    @PostMapping
    public ResponseEntity<ApiResponse> assignTenantToRoom(TenantRequestDto dto){
        return null;
    }

//    @PostMapping
    public ResponseEntity<ApiResponse> unassignTenantFromRoom(TenantRequestDto dto){
        return null;
    }
}
