package com.theniche.colivin.tenants;
import com.theniche.colivin.common.payload.ApiResponse;
import com.theniche.colivin.tenants.dto.TenantRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final TenantService tenantService;
    public TenantController(TenantService tenantService, TenantMapper tenantMapper) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
         var tenants = tenantService.getTenants();
         return new ResponseEntity<>(tenants, HttpStatus.OK);
    }

    // register a new tenant
    @PostMapping
    public ResponseEntity<ApiResponse<Tenant>> createNewTenant(@RequestBody TenantRequest request) {
       var savedEntity= tenantService.registerNewTenant(request);
       return  new ResponseEntity<>(new ApiResponse<>(true, "Tenant created successfully", savedEntity), HttpStatus.OK);
    }
    // update tenant

    @PutMapping("/{tenantId}")
    public ResponseEntity updateTenant(@PathVariable("tenantId") UUID tenantId, @RequestBody TenantRequest request){
        var updatedTenant = tenantService.updateTenant(tenantId, request);
        return new ResponseEntity(new ApiResponse<>(true, "Tenant updated successfully", updatedTenant), HttpStatus.OK);
    }



    // delete tenant

    // upload tenant documents
}
