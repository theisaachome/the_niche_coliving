package com.theniche.colivin.service.impl;

import com.theniche.colivin.entity.TenantDocument;
import com.theniche.colivin.exception.ResourceNotFoundException;
import com.theniche.colivin.payload.ApiListResponse;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.document.DocumentRequest;
import com.theniche.colivin.payload.document.DocumentResponse;
import com.theniche.colivin.repository.TenantDocumentRepository;
import com.theniche.colivin.repository.TenantRepository;
import com.theniche.colivin.service.TenantDocumentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TenantDocumentServiceImpl implements TenantDocumentService {
    private final TenantDocumentRepository tenantDocumentRepository;
    private final TenantRepository tenantRepository;

    public TenantDocumentServiceImpl(TenantDocumentRepository tenantDocumentRepository, TenantRepository tenantRepository) {
        this.tenantDocumentRepository = tenantDocumentRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public ApiResponse<ResponseData> addDocument(UUID tenantId, DocumentRequest document) {
        var tenant = tenantRepository.findById(tenantId)
                .orElseThrow(()->new ResourceNotFoundException("Tenant","ID",tenantId));
        var documentEntity =  TenantDocument.builder()
                .documentType(document.documentType())
                .documentNumber(document.documentNumber())
                .tenant(tenant)
                .build();
        var savedDocument= tenantDocumentRepository.save(documentEntity);
        return new ApiResponse<>("success",
                "addDocument operation successfully",
                new ResponseData(savedDocument.getId(),
                        savedDocument.getDocumentType(),
                        savedDocument.getCreatedDate(),
                        savedDocument.getCreatedBy()));
    }

    @Override
    public ApiResponse<ResponseData> updateDocument( UUID documentId, DocumentRequest document) {
        var existingDocument = tenantDocumentRepository.findById(documentId)
                .orElseThrow(()->new ResourceNotFoundException("Document","ID",documentId));
        existingDocument.setDocumentType(document.documentType());
        existingDocument.setDocumentNumber(document.documentNumber());
        var savedDocument = tenantDocumentRepository.save(existingDocument);
        return new ApiResponse<>("success",
                "updateDocument operation successfully",
                new ResponseData(savedDocument.getId(),
                        savedDocument.getDocumentType(),
                        savedDocument.getCreatedDate(),
                        savedDocument.getCreatedBy()));
    }

    @Override
    public ApiResponse<DocumentResponse> getTenantDocumentById( UUID documentId) {
        var existingDocument = tenantDocumentRepository.findById(documentId)
                .orElseThrow(()->new ResourceNotFoundException("Document","ID",documentId));
        var responseData = new DocumentResponse(
                existingDocument.getId(),
                existingDocument.getDocumentType(),
                existingDocument.getDocumentNumber(),
                existingDocument.getFilePath(),
                existingDocument.getUploadedAt(),
                existingDocument.getCreatedBy(),
                existingDocument.getUpdatedBy(),
                existingDocument.getCreatedDate(),
                existingDocument.getUpdatedDate()
        );
        return new ApiResponse<>("success",
                "deleteRoom operation successfully",
                responseData);
    }

    @Override
    public ApiListResponse<List<DocumentResponse>> getTenantDocuments(UUID tenantId) {
        var documents = tenantDocumentRepository.findAllByTenantId(tenantId);
       var responseDocuments=  documents.stream()
                .map(data->new DocumentResponse(
                        data.getId(),
                        data.getDocumentType(),
                        data.getDocumentNumber(),
                        data.getFilePath(),
                        data.getUploadedAt(),
                        data.getCreatedBy(),
                        data.getUpdatedBy(),
                        data.getCreatedDate(),
                        data.getUpdatedDate()))
                .collect(Collectors.toList());
        return new ApiListResponse<>(responseDocuments);
    }

    @Override
    public ApiResponse<ResponseData> deleteDocument( UUID documentId) {
        var existingDocument = tenantDocumentRepository.findById(documentId).orElseThrow(()->new ResourceNotFoundException("Document","ID",documentId));
        existingDocument.setDeleted(true);
        return new ApiResponse<>("success",
                "deleteDocument operation successfully",
                new ResponseData(existingDocument.getId(),
                        existingDocument.getDocumentType(),
                        existingDocument.getCreatedDate(),
                        existingDocument.getCreatedBy()));
    }
}
