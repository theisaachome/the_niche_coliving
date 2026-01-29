package com.theniche.colivin.roomassignment;

import com.theniche.colivin.common.exception.ResourceNotFoundException;
import com.theniche.colivin.room.RoomRepository;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentCreatedResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentRequest;
import com.theniche.colivin.tenants.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {
    private final RoomAssignmentRepository roomAssignmentRepository;
    private final RoomRepository roomRepository;
    private final TenantRepository tenantRepository;

    public RoomAssignmentServiceImpl(RoomAssignmentRepository roomAssignmentRepository, RoomRepository roomRepository, TenantRepository tenantRepository) {
        this.roomAssignmentRepository = roomAssignmentRepository;
        this.roomRepository = roomRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public RoomAssignmentCreatedResponse create(RoomAssignmentRequest dto) {
        // 1️⃣ Validate dates
        if (dto.leaseEndDate() != null &&
                dto.leaseEndDate().isBefore(dto.leaseStartDate())) {
            throw new IllegalArgumentException("Lease end date must be after start date");
        }
        // find Room by id
        var room = roomRepository.findById(dto.roomId())
                .orElseThrow(()-> new ResourceNotFoundException("Room", "id", dto.roomId()));
        var tenant = tenantRepository.findById(dto.tenantId())
                .orElseThrow(()-> new ResourceNotFoundException("Tenant", "id", dto.tenantId()));
        //
        var roomAssignment = new RoomAssignment()
                .setHouse(room.getHouse())
                .setRoom(room)
                .setTenant(tenant)
                .setDepositAmount(dto.depositAmount())
                .setDepositStatus(RoomAssignment.DepositStatus.HELD)
                .setAssignmentStatus(AssignmentStatus.ACTIVE)
                .setNotes(dto.notes())
                .setLeaseStartDate(dto.leaseStartDate())
                .setLeaseEndDate(dto.leaseEndDate());
        var result = roomAssignmentRepository.save(roomAssignment);

        return new RoomAssignmentCreatedResponse(result.getId(),"Assignment successfully created");
    }

    @Override
    public RoomAssignment findById(UUID id) {
        return null;
    }

    @Override
    public RoomAssignment update(UUID roomAssignmentId, RoomAssignment roomAssignment) {
        return null;
    }
}
