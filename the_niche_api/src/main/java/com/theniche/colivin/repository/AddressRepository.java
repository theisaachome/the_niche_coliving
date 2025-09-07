package com.theniche.colivin.repository;
import com.theniche.colivin.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    List<Address> findByTenantId(UUID tenantId);
}
