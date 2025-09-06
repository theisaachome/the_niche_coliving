package com.theniche.colivin.repository;
import com.theniche.colivin.entity.TenantDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TenantDocumentRepository extends JpaRepository<TenantDocument, UUID> {

    List<TenantDocument> findAllByTenantId(UUID tenantId);
}
