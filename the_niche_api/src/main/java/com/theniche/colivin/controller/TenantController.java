package com.theniche.colivin.controller;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.tenant.TenantRequestDto;
import com.theniche.colivin.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    // let the tenant provide the information
    @PostMapping
    public ResponseEntity<ApiResponse> createTenant(@RequestBody TenantRequestDto dto){
        var result = tenantService.createNewTenant(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{tenantId}")
    public ResponseEntity<ApiResponse> updateTenant(@PathVariable("tenantId")UUID tenantId, @RequestBody TenantRequestDto dto){
       var result =  tenantService.updateTenant(tenantId, dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{tenantId}")
    public ResponseEntity<ApiResponse>  deleteTenant(@PathVariable UUID tenantId){
        var result = tenantService.deleteTenant(tenantId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{tenantId}/documents")
    public ResponseEntity<ApiResponse> addTenantDocument(@PathVariable("tenantId")UUID tenantId,@Valid @RequestBody TenantRequestDto dto){return null;}
}
