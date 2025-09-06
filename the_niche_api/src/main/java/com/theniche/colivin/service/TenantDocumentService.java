package com.theniche.colivin.service;

import com.theniche.colivin.entity.TenantDocument;
import com.theniche.colivin.payload.ApiListResponse;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.document.DocumentRequest;
import com.theniche.colivin.payload.document.DocumentResponse;

import java.util.List;
import java.util.UUID;

public interface TenantDocumentService {

    ApiResponse<ResponseData> addDocument(UUID tenantId, DocumentRequest dto);
    ApiResponse<ResponseData> updateDocument(UUID documentId, DocumentRequest document);
    ApiResponse<DocumentResponse> getTenantDocumentById(UUID documentId);
    ApiListResponse<List<DocumentResponse>> getTenantDocuments(UUID tenantId);
    ApiResponse<ResponseData> deleteDocument(UUID documentId);
}
