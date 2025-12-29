package com.theniche.colivin.tenants;
import com.theniche.colivin.common.payload.PageApiResponse;
import com.theniche.colivin.common.payload.PageRequestDto;
import com.theniche.colivin.tenants.dto.TenantRequest;
import com.theniche.colivin.tenants.dto.TenantResponse;
import com.theniche.colivin.tenants.dto.TenantSearchFilters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final TenantService tenantService;
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/search")
    public PageApiResponse<TenantResponse> searchTenants(
            @ModelAttribute TenantSearchFilters filters,
            @ModelAttribute PageRequestDto pageRequestDto
            ){
      return  tenantService.searchTenant(filters, pageRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<TenantResponse>> getAllTenants() {
         var tenants = tenantService.getTenants();
         return new ResponseEntity<>(tenants, HttpStatus.OK);
    }

    // register a new tenant
    @PostMapping
    public ResponseEntity<TenantResponse> createNewTenant(@RequestBody TenantRequest request) {
       var savedEntity= tenantService.registerNewTenant(request);
       return  new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }
    // update tenant
    @PutMapping("/{tenantId}")
    public ResponseEntity<String> updateTenant(@PathVariable("tenantId") UUID tenantId, @RequestBody TenantRequest request){
        var updatedTenant = tenantService.updateTenant(tenantId, request);
        return new ResponseEntity<>( String.format("%s updated successfully.",updatedTenant.tenantCode()), HttpStatus.OK);
    }


    // delete tenant
    @DeleteMapping("/{tenantId}")
    public ResponseEntity<String>  deleteTenant(@PathVariable("tenantId") UUID tenantId) {
        tenantService.deleteTenant(tenantId);
        return new ResponseEntity<>("Content data deleted successfully", HttpStatus.OK);
    }
    // upload tenant documents
}
