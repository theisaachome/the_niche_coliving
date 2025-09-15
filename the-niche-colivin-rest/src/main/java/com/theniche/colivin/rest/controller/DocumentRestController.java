package com.theniche.colivin.rest.controller;

import com.theniche.colivin.domain.service.DocumentService;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.document.DocumentRequest;
import com.theniche.colivin.rest.mapper.doucment.DocumentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentRestController {

    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    public DocumentRestController(DocumentService documentService, DocumentMapper documentMapper) {
        this.documentService = documentService;
        this.documentMapper = documentMapper;
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<?> updateDocument(@PathVariable("documentId")UUID id, @RequestBody DocumentRequest request) {
        var entity = documentMapper.requestToEntity(request);
        var savedEntity=documentService.update(id, entity);
        return new ResponseEntity<>(new ApiResponse("success","document updated successfully",savedEntity.getId())
        , HttpStatus.OK);
    }
    @DeleteMapping("/{documentId}")
    public ResponseEntity<?> deleteDocument(@PathVariable("documentId")UUID id) {
        var result = documentService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("success","document deleted successfully",result.getId()));
    }
}
