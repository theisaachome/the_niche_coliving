package com.theniche.colivin.tenants.dto;

import com.theniche.colivin.common.domain.TenantStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TenantDetailResponse (
        UUID id,
        String tenantCode,
        String fullName,
        String email,
        String phone,
        TenantStatus status,
        // Document
        // address
        // contact
        // emergency contact
        // audit fields
        boolean deleted,
        String createdBy,
        String updatedBy,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
){}
