package com.theniche.colivin.rest.controller;
import com.theniche.colivin.domain.entity.Tenant;
import com.theniche.colivin.domain.service.TenantService;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.tenant.TenantRequest;
import com.theniche.colivin.rest.dto.tenant.TenantResponse;
import com.theniche.colivin.rest.mapper.tenant.TenantMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/tenants")
public class TenantRestController extends BaseController<Tenant, TenantRequest, TenantResponse> {

    private final TenantService tenantService;
    private TenantMapper tenantMapper;

    public TenantRestController(TenantService tenantService, TenantMapper tenantMapper) {
        super(tenantService, tenantMapper);
        this.tenantService = tenantService;
        this.tenantMapper = tenantMapper;
    }

    @PutMapping("/{tenantId}")
    @Override
    protected  ResponseEntity<ApiResponse> update(@PathVariable("tenantId") UUID id, @RequestBody TenantRequest requestDto) {
        var entity = tenantMapper.requestToEntity(requestDto);
        var results= tenantService.update(id, entity);
        return new ResponseEntity<>(new ApiResponse("success","Update successful",results.getId()), HttpStatus.OK);
    }
}
