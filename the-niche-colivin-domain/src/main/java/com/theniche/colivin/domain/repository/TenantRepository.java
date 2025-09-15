package com.theniche.colivin.domain.repository;

import com.theniche.colivin.domain.common.BaseRepository;
import com.theniche.colivin.domain.entity.Tenant;

import java.util.Optional;

public interface TenantRepository extends BaseRepository<Tenant> {

    Optional<Tenant> findTenantByEmailAndPhone(String email, String phone);
}
