package com.theniche.colivin.rest.controller;
import com.theniche.colivin.domain.common.GenericSpecification;
import com.theniche.colivin.domain.common.SearchCriteria;
import com.theniche.colivin.domain.entity.Tenant;
import com.theniche.colivin.domain.exception.ResourceNotFoundException;
import com.theniche.colivin.domain.service.TenantService;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.tenant.TenantRequest;
import com.theniche.colivin.rest.dto.tenant.TenantResponse;
import com.theniche.colivin.rest.mapper.tenant.TenantMapper;
import com.theniche.colivin.rest.utils.PageRequestDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/search")
    ResponseEntity<TenantResponse> searchTenantOne(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "fullName", required = false) String fullName
    ){
        GenericSpecification<Tenant> specBuilder = new GenericSpecification<>();
        if(StringUtils.isNotBlank(email)){
            specBuilder.add(new SearchCriteria("email",email, SearchCriteria.Operation.EQUAL));
        }
        if(StringUtils.isNotBlank(phone)){
            specBuilder.add(new SearchCriteria("phone",phone, SearchCriteria.Operation.EQUAL));
        }
        if(StringUtils.isNotBlank(fullName)){
            specBuilder.add(new SearchCriteria("fullName",fullName, SearchCriteria.Operation.LIKE));
        }
        var searchResult = tenantService.searchOne(specBuilder.build()).orElseThrow(()-> new ResourceNotFoundException("No Tenant for",
                String.format("%s %s %s", email, phone, fullName),
                null));
        return new ResponseEntity<>(tenantMapper.entityToResponse(searchResult),HttpStatus.OK);
    }

    @GetMapping("/search#")
    ResponseEntity<List<TenantResponse>> searchTenants (
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "tenantCode", required = false) String tenantCode,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "fullName", required = false) String fullName,
            @ModelAttribute PageRequestDto pageRequestDto){
        GenericSpecification<Tenant> specBuilder = new GenericSpecification<>();
        var pagedList= tenantService.searchList(specBuilder.build(),pageRequestDto.toPageable());

    }


}
