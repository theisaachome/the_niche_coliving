package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.entity.Document;
import com.theniche.colivin.domain.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocumentService extends BaseService<Document> {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        super(documentRepository);
        this.documentRepository = documentRepository;
    }

    @Override
    public Document update(UUID id, Document entity) {
        return null;
    }
}
