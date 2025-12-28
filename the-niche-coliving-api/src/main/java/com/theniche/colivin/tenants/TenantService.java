package com.theniche.colivin.tenants;


import com.theniche.colivin.tenants.dto.TenantRequest;

import java.util.List;
import java.util.UUID;

public interface TenantService {

    Tenant registerNewTenant(TenantRequest dto);
    Tenant updateTenant(UUID id, TenantRequest dto);
    Tenant getTenant(UUID id);
    void deleteTenant(UUID id);
    List<Tenant> getTenants();

}
