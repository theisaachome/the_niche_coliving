package com.theniche.colivin.tenants.dto;

public record TenantResponse(
        String id,
        String tenantCode,
        String fullName,
        String email,
        String phone,
        String status
) {}
