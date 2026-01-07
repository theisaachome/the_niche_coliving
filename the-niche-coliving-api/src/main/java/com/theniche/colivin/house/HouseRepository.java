package com.theniche.colivin.house;
import com.theniche.colivin.common.repository.BaseRepository;
import com.theniche.colivin.house.dto.HouseOverviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface HouseRepository extends BaseRepository<House>{

    @Query("""
        SELECT new com.theniche.colivin.house.dto.HouseOverviewResponse(
          h.id,
          h.houseCode,
          h.name,
          h.location,
          h.houseStatus,
          COUNT(r),
          SUM(CASE WHEN r.roomStatus = 'AVAILABLE' THEN 1 ELSE 0 END)
        )
        FROM House h
        LEFT JOIN h.rooms r
        GROUP BY h.id
        """)
    Page<HouseOverviewResponse> findHouseOverview(Pageable pageable);
}
