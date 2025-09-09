package com.theniche.colivin.rest.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public abstract class BaseResponseDto {
    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private String createdBy;
    private String updatedBy;
    private Long version;

}
