package com.theniche.colivin.repository;
import com.theniche.colivin.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, UUID> {
}
