package com.theniche.colivin.rest.mapper.doucment;
import com.theniche.colivin.domain.entity.Document;
import com.theniche.colivin.rest.dto.document.DocumentRequest;
import com.theniche.colivin.rest.dto.document.DocumentResponse;
import com.theniche.colivin.rest.mapper.BaseMapper;
import org.springframework.stereotype.Component;


@Component
public class DocumentMapper implements BaseMapper<Document, DocumentRequest, DocumentResponse> {
    @Override
    public Document requestToEntity(DocumentRequest requestDto) {
        return Document.builder()
                .documentNumber(requestDto.documentNumber())
                .documentType(requestDto.documentType())
                .build();
    }

    @Override
    public DocumentResponse entityToResponse(Document entity) {
        return new DocumentResponse(
                entity.getId(),
                entity.getDocumentType(),
                entity.getDocumentNumber(),
                entity.getFilePath(),
                entity.getUploadedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate());
    }
}
