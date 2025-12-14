package com.theniche.colivin.tenants;


import com.theniche.colivin.common.payload.ApiResponse;
import com.theniche.colivin.tenants.dto.TenantRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final TenantService tenantService;
    private final TenantMapper tenantMapper;
    public TenantController(TenantService tenantService, TenantMapper tenantMapper) {
        this.tenantService = tenantService;
        this.tenantMapper = tenantMapper;
    }

    // register a new tenant
    @PostMapping
    public ResponseEntity createNewTenant(@RequestBody TenantRequest request) {
       var savedEntity= tenantService.save(tenantMapper.toEntity(request));
       return  new ResponseEntity<>(new ApiResponse<>(true, "Tenant created successfully", savedEntity), HttpStatus.OK);
    }
    // update tenant

    // delete tenant

    // upload tenant documents
}
