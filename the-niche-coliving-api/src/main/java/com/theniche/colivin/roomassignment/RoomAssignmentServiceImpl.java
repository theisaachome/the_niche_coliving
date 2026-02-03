package com.theniche.colivin.roomassignment;

import com.theniche.colivin.common.exception.BusinessException;
import com.theniche.colivin.common.exception.ResourceNotFoundException;
import com.theniche.colivin.common.payload.PagedApiResponse;
import com.theniche.colivin.room.RoomRepository;
import com.theniche.colivin.roomassignment.dto.*;
import com.theniche.colivin.tenants.TenantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {
    private final RoomAssignmentRepository roomAssignmentRepository;
    private final RoomRepository roomRepository;
    private final TenantRepository tenantRepository;
    private final RoomAssignmentMapper roomAssignmentMapper;

    public RoomAssignmentServiceImpl(RoomAssignmentRepository roomAssignmentRepository, RoomRepository roomRepository, TenantRepository tenantRepository, RoomAssignmentMapper roomAssignmentMapper) {
        this.roomAssignmentRepository = roomAssignmentRepository;
        this.roomRepository = roomRepository;
        this.tenantRepository = tenantRepository;
        this.roomAssignmentMapper = roomAssignmentMapper;
    }

    @Override
    public RoomAssignmentCreatedResponse create(RoomAssignmentRequest dto) {

        // 1️⃣ Validate dates
        if (dto.leaseEndDate() != null &&
                dto.leaseEndDate().isBefore(dto.leaseStartDate())) {
            throw new IllegalArgumentException("Lease end date must be after start date");
        }
        // 2️⃣ Lock room to avoid race conditions
        var room = roomRepository.findById(dto.roomId())
                .orElseThrow(()-> new ResourceNotFoundException("Room", "id", dto.roomId()));

        // 3️⃣ Check room capacity
        long activeAssignments =
                roomAssignmentRepository.countActiveByRoomId(room.getId());

        if (activeAssignments >= room.getCapacity()) {
            throw new BusinessException("Room is already full");
        }

        // 4️⃣ Ensure tenant has no active assignment
        boolean tenantHasActive =
                roomAssignmentRepository.existsActiveByTenantId(dto.tenantId());

        if (tenantHasActive) {
            throw new BusinessException("Tenant already has an active room assignment");
        }

        // 5️⃣ Load tenant
        var tenant = tenantRepository.findById(dto.tenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant", "id", dto.tenantId()));

        // 6️⃣ Create assignment
        var roomAssignment = new RoomAssignment()
                .setHouse(room.getHouse())
                .setRoom(room)
                .setTenant(tenant)
                .setLeaseStartDate(dto.leaseStartDate())
                .setLeaseEndDate(dto.leaseEndDate())
                .setDepositAmount(dto.depositAmount())
                .setDepositStatus(RoomAssignment.DepositStatus.HELD)
                .setAssignmentStatus(AssignmentStatus.ACTIVE)
                .setNotes(dto.notes());

        roomAssignmentRepository.save(roomAssignment);

        return new RoomAssignmentCreatedResponse(
                roomAssignment.getId(),
                "Assignment successfully created"
        );
    }

    @Override
    public RoomAssignment findById(UUID id) {
        return roomAssignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomAssignment", "id", id));
    }

    @Override
    public RoomAssignmentDetailsResponse findDetails(UUID id) {
        return this.roomAssignmentRepository.findDetailsById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomAssignment", "id", id));
    }

    // update the room assignment
    @Override
    public RoomAssignment update(UUID roomAssignmentId, RoomAssignment roomAssignment) {
        return null;
    }

    @Override
    public List<RoomAssignmentOverviewResponse> findRoomAssignmentsByRoomId(UUID roomId, AssignmentStatus status) {
       return this.roomAssignmentRepository.findByRoomIdAndAssignmentStatus(roomId,status);
    }

    @Override
    public PagedApiResponse<RoomAssignmentListResponse> listAssignments(Long houseId, AssignmentStatus status, LocalDate endingBefore, Pageable pageable) {
      var pagedResults = roomAssignmentRepository.findRoomAssignments(houseId, status, endingBefore, pageable);
      return  new PagedApiResponse<>(
              pagedResults.getContent(),
              pagedResults.getNumber(),
              pagedResults.getSize(),
              pagedResults.getTotalElements(),
              pagedResults.getTotalPages(),
              pagedResults.isLast()
      );
    }

    // move rooms
}
