package com.theniche.colivin.roomassignment;
import com.theniche.colivin.common.repository.BaseRepository;
import com.theniche.colivin.room.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

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

    List<RoomAssignment> findByRoomIdAndAssignmentStatus(UUID roomId, AssignmentStatus assignmentStatus);
}
