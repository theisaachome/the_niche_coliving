package com.theniche.colivin.tenants;


import com.theniche.colivin.common.payload.PageApiResponse;
import com.theniche.colivin.common.payload.PageRequestDto;
import com.theniche.colivin.tenants.dto.TenantDetailResponse;
import com.theniche.colivin.tenants.dto.TenantRequest;
import com.theniche.colivin.tenants.dto.TenantResponse;
import com.theniche.colivin.tenants.dto.TenantSearchFilters;

import java.util.List;
import java.util.UUID;

public interface TenantService {

    TenantResponse registerNewTenant(TenantRequest dto);
    TenantResponse updateTenant(UUID id, TenantRequest dto);
    TenantDetailResponse getTenant(UUID id);
    void deleteTenant(UUID id);
    List<TenantResponse> getTenants();
    PageApiResponse<TenantResponse> searchTenant(TenantSearchFilters  filters, PageRequestDto  pageRequest);

}
