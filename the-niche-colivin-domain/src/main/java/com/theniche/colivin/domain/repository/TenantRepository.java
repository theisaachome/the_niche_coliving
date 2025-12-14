package com.theniche.colivin.domain.repository;

import com.theniche.colivin.domain.entity.Tenant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TenantRepository extends BaseRepository<Tenant> {

    Optional<Tenant> findTenantByEmailAndPhone(String email, String phone);
    @Query(
        """
        SELECT t FROM Tenant t 
          LEFT JOIN FETCH  t.addresses
          LEFT JOIN FETCH t.documents
          WHERE t.id= :id        
        """
    )
    Optional<Tenant>  findByIdWithDetails(@Param("id")UUID id);

    @Query(
            """
            SELECT t FROM Tenant  t 
            LEFT JOIN FETCH t.roomAssignments ra            
            LEFT JOIN FETCH ra.room
            WHERE t.id = :id
            """
    )
    Optional<Tenant> findByIdWithAssignments(@Param("id")UUID id);
}
