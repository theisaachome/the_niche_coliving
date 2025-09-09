package com.theniche.colivin.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="addresses")
public class Address extends BaseEntity{

    @Column(nullable = false)
    private String addressLine1;
    private String addressLine2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;
    private String postalCode;

    @Column(nullable = false)
    private String country;
    // tenant-id
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}
