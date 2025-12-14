package com.theniche.colivin.tenants;

import com.theniche.colivin.common.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TenantService extends BaseService<Tenant> {

    private final TenantRepository tenantRepository;
    public TenantService(TenantRepository tenantRepository) {
        super(tenantRepository);
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant update(UUID id, Tenant entity) {
        return null;
    }
}
