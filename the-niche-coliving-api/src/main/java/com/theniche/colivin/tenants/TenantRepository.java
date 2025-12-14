package com.theniche.colivin.tenants;

import com.theniche.colivin.common.repository.BaseRepository;

public interface TenantRepository extends BaseRepository<Tenant> {

    /*
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

     */
}
