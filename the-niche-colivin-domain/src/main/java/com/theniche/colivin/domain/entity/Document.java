package com.theniche.colivin.domain.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenant_documents")
public class Document extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "tenant_id")
    private Tenant tenant;
    @Column(name = "document_type", nullable = false,length = 50)
    private String documentType;
    @Column(name = "document_number",nullable = false,length = 100)
    private String documentNumber;
    private String filePath;
    @CreatedDate
    private LocalDateTime uploadedAt;
}
