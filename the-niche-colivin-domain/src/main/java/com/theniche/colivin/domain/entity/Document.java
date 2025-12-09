package com.theniche.colivin.domain.entity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


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

    public Tenant getTenant() {
        return tenant;
    }

    public Document setTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public String getDocumentType() {
        return documentType;
    }

    public Document setDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public Document setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public Document setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public Document setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
        return this;
    }
}
