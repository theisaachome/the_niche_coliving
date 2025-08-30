package com.theniche.colivin.repository;

import com.theniche.colivin.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
