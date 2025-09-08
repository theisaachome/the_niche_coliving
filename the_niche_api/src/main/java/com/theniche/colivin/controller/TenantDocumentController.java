package com.theniche.colivin.controller;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.document.DocumentRequest;
import com.theniche.colivin.payload.document.DocumentResponse;
import com.theniche.colivin.service.TenantDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/*
@RestController
@RequestMapping("/api/v1/documents")
public class TenantDocumentController {

    private final TenantDocumentService tenantDocumentService;

    public TenantDocumentController(TenantDocumentService tenantDocumentService) {
        this.tenantDocumentService = tenantDocumentService;
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<ApiResponse<DocumentResponse>> getTenantDocument(@PathVariable UUID documentId) {
        var results = tenantDocumentService.getTenantDocumentById(documentId);
        // fetch document by id
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<ApiResponse<ResponseData>> updateTenantDocument(
            @PathVariable UUID documentId,
            @RequestBody DocumentRequest request) {
        // update document
        var result = tenantDocumentService.updateDocument(documentId,request);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<ApiResponse<ResponseData>> deleteTenantDocument(@PathVariable UUID documentId) {
        // delete document
        var result = tenantDocumentService.deleteDocument(documentId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}

 */
