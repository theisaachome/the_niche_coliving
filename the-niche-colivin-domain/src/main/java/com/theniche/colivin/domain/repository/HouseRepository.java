package com.theniche.colivin.domain.repository;

import com.theniche.colivin.domain.common.BaseRepository;
import com.theniche.colivin.domain.entity.House;
import com.theniche.colivin.domain.payload.HouseSummary;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseRepository extends BaseRepository<House> {
/*
    @Query("""
            SELECT new com.theniche.colivin.domain.payload.HouseSummary(
                    h.id,
                    h.name,
                    h.address,
                    COUNT(r.id),
                    SUM(CASE WHEN r.capacity > SIZE(r.tenants) THEN 1 ELSE 0 END),
                    SUM(SIZE(r.tenants)),
                    h.notes
                    )
            FROM House  h 
            LEFT JOIN h.rooms r 
            GROUP BY h.id,h.name,h.address,h.notes
        """
    )
    List<HouseSummary> findHouseSummaries();

 */
}
