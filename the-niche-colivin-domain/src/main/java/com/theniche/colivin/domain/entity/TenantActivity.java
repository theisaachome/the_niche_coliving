package com.theniche.colivin.domain.entity;


import com.theniche.colivin.domain.common.ActivityType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class TenantActivity extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    @ManyToOne(optional = false)
    private Room room;
    private LocalDateTime activityDate;
    private ActivityType activityType;
    private String notes;

}
