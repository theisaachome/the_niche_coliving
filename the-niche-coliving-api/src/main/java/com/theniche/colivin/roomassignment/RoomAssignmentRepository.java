package com.theniche.colivin.roomassignment;
import com.theniche.colivin.common.repository.BaseRepository;
import com.theniche.colivin.room.Room;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentDetailsResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentListResponse;
import com.theniche.colivin.roomassignment.dto.RoomAssignmentOverviewResponse;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomAssignmentRepository extends BaseRepository<RoomAssignment> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from Room r where r.id = :id")
    Optional<Room> findByIdForUpdate(UUID id);

    @Query("""
        select count(a)
        from RoomAssignment a
        where a.room.id = :roomId
          and a.assignmentStatus = 'ACTIVE'
    """)
    Long countActiveByRoomId(UUID roomId);

    boolean existsByTenantIdAndAssignmentStatus(UUID tenantId, AssignmentStatus status);


    boolean existsActiveByTenantId(UUID uuid);
    @Query("""
            SELECT new com.theniche.colivin.roomassignment.dto.RoomAssignmentOverviewResponse(
                     ra.id,
                     t.fullName,
                     ra.assignmentStatus,
                     ra.leaseStartDate,
                     ra.leaseEndDate,
                     ra.depositAmount)
                     FROM RoomAssignment  ra
                     JOIN Tenant t ON ra.tenant.id = t.id
                     WHERE ra.room.id = :roomId
                     AND ra.assignmentStatus IN (:assignmentStatus)
        """)
    List<RoomAssignmentOverviewResponse> findByRoomIdAndAssignmentStatus(@Param("roomId") UUID roomId, @Param("assignmentStatus") AssignmentStatus assignmentStatus);

    @Query("""
        SELECT new com.theniche.colivin.roomassignment.dto.RoomAssignmentDetailsResponse(
            ra.id,
            ra.assignmentStatus,
            ra.leaseStartDate,
            ra.leaseEndDate,
            ra.depositAmount,
            ra.depositStatus,
            ra.notes,
            t.id,
            t.fullName,
            t.phone,
            t.email,
            r.id,
            r.roomNumber,
            r.capacity,
            h.id,
            h.name
        )
        FROM RoomAssignment ra
        JOIN ra.tenant t
        JOIN ra.room r
        JOIN ra.house h
        WHERE ra.id = :assignmentId
    """)
    Optional<RoomAssignmentDetailsResponse> findDetailsById(UUID assignmentId);


    @Query("""
    select new com.theniche.colivin.roomassignment.dto.RoomAssignmentListResponse(
        ra.id,
        h.id,
        h.name,
        r.id,
        r.roomNumber,
        r.roomType,
        t.id,
        t.fullName,
        ra.leaseStartDate,
        ra.leaseEndDate,
        ra.assignmentStatus,
        ra.monthlyRent
    )
    from RoomAssignment ra
    join ra.house h
    join ra.room r
    join ra.tenant t
    where (:houseId is null or h.id = :houseId)
      and (:status is null or ra.assignmentStatus = :status)
      and (:endingBefore is null or ra.leaseEndDate <= :endingBefore)
""")
    Page<RoomAssignmentListResponse> findRoomAssignments(
            @Param("houseId") Long houseId,
            @Param("status") AssignmentStatus status,
            @Param("endingBefore") LocalDate endingBefore,
            Pageable pageable
    );

}
