package com.theniche.colivin.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenant_documents")
public class TenantDocument extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "tenant_id")
    private Tenant tenant;
    @Column(name = "document_type", nullable = false,length = 50)
    private String documentType;
    @Column(name = "document_number",nullable = false,length = 100)
    private String documentNumber;
    private String filePath;
    private LocalDateTime uploadedAt;
}
