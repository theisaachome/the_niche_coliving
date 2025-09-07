package com.theniche.colivin.service;

import com.theniche.colivin.entity.Tenant;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.address.AddressDto;
import com.theniche.colivin.payload.tenant.TenantRequestDto;
import com.theniche.colivin.payload.tenant.TenantResponseDto;

import java.util.UUID;

public interface TenantService {

    ApiResponse<ResponseData> createNewTenant(TenantRequestDto dto);
    ApiResponse<ResponseData> updateTenant(UUID tenantId, TenantRequestDto dto);
    ApiResponse<TenantResponseDto> getTenantDetails(UUID tenantId);
    ApiResponse<ResponseData> deleteTenant(UUID tenantId);
    ApiResponse<ResponseData> addTenantAddress(UUID tenantId, AddressDto dto);
}
