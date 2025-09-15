package com.theniche.colivin.rest.controller;
import com.theniche.colivin.domain.common.GenericSpecification;
import com.theniche.colivin.domain.common.SearchCriteria;
import com.theniche.colivin.domain.entity.Tenant;
import com.theniche.colivin.domain.exception.ResourceNotFoundException;
import com.theniche.colivin.domain.service.TenantService;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.PageApiResponse;
import com.theniche.colivin.rest.dto.address.AddressDto;
import com.theniche.colivin.rest.dto.document.DocumentRequest;
import com.theniche.colivin.rest.dto.tenant.TenantDetailsResponse;
import com.theniche.colivin.rest.dto.tenant.TenantRequest;
import com.theniche.colivin.rest.dto.tenant.TenantResponse;
import com.theniche.colivin.rest.dto.tenant.TenantSearchFilters;
import com.theniche.colivin.rest.mapper.address.AddressMapper;
import com.theniche.colivin.rest.mapper.doucment.DocumentMapper;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/tenants")
public class TenantRestController extends BaseController<Tenant, TenantRequest, TenantResponse> {

    private final TenantService tenantService;
    private final TenantMapper tenantMapper;
    private final AddressMapper addressMapper;
    private final DocumentMapper documentMapper;

    public TenantRestController(TenantService tenantService,
                                TenantMapper tenantMapper,
                                AddressMapper addressMapper, DocumentMapper documentMapper) {
        super(tenantService, tenantMapper);
        this.tenantService = tenantService;
        this.tenantMapper = tenantMapper;
        this.addressMapper = addressMapper;
        this.documentMapper = documentMapper;
    }

    @PutMapping("/{tenantId}")
    @Override
    protected  ResponseEntity<ApiResponse> update(@PathVariable("tenantId") UUID id, @RequestBody TenantRequest requestDto) {
        var entity = tenantMapper.requestToEntity(requestDto);
        var results= tenantService.update(id, entity);
        return new ResponseEntity<>(new ApiResponse("success","Update successful",results.getId()), HttpStatus.OK);
    }

    @GetMapping("/{tenantId}/details")
    ResponseEntity<TenantDetailsResponse> getTenantDetailsById(@PathVariable("tenantId") UUID id){
        var entityResult = tenantService.getTenantDetails(id);
        return new ResponseEntity<>(tenantMapper.entityToDetailsResponse(entityResult), HttpStatus.OK);
    }

    @GetMapping("/find")
    ResponseEntity<TenantResponse> findTenant(
            @RequestParam(value = "tenantCode", required = false) String tenantCode,
            @RequestParam(value = "phone", required = false) String phone
    ){
        GenericSpecification<Tenant> specBuilder = new GenericSpecification<>();
        if(StringUtils.isNotBlank(tenantCode)){
            specBuilder.add(new SearchCriteria("tenantCode",tenantCode, SearchCriteria.Operation.EQUAL));
        }
        if(StringUtils.isNotBlank(phone)){
            specBuilder.add(new SearchCriteria("phone",phone, SearchCriteria.Operation.EQUAL));
        }
        var searchResult = tenantService.searchOne(specBuilder.build()).orElseThrow(()-> new ResourceNotFoundException("No Tenant ",
                String.format("%s %s ", tenantCode, phone),
                null));
        return new ResponseEntity<>(tenantMapper.entityToResponse(searchResult),HttpStatus.OK);
    }

    @GetMapping("/search")
    ResponseEntity<PageApiResponse> searchTenants (
            @ModelAttribute TenantSearchFilters filters,
            @ModelAttribute PageRequestDto pageRequestDto){
        GenericSpecification<Tenant> specBuilder = new GenericSpecification<>();
        if(StringUtils.isNotBlank(filters.getTenantCode())){
            specBuilder.add(new SearchCriteria("tenantCode",filters.getTenantCode(), SearchCriteria.Operation.EQUAL));
        }
        if(StringUtils.isNotBlank(filters.getEmail())){
            specBuilder.add(new SearchCriteria("email",filters.getEmail(), SearchCriteria.Operation.OR));
        }
        if(StringUtils.isNotBlank(filters.getPhone())){
            specBuilder.add(new SearchCriteria("phone",filters.getPhone(), SearchCriteria.Operation.OR));
        }
        if(StringUtils.isNotBlank(filters.getFullName())){
            specBuilder.add(new SearchCriteria("fullName",filters.getFullName(), SearchCriteria.Operation.LIKE));
        }
        var pagedList= tenantService.searchList(specBuilder.build(),pageRequestDto.toPageable());
        var dtoList =pagedList.getContent().stream().map(mapper::entityToResponse).collect(Collectors.toList());
        var result= new PageApiResponse(
                dtoList,
                pagedList.getNumber(),
                pagedList.getSize(),
                pagedList.getTotalElements(),
                pagedList.getTotalPages(),
                pagedList.isLast()
        );
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @PostMapping("/{tenantId}/documents")
    ResponseEntity<?> addTenantDocument(@PathVariable("tenantId") UUID id,@RequestBody DocumentRequest request){
        var documentEntity = documentMapper.requestToEntity(request);
        var savedTenant = tenantService.addTenantDocument(id, documentEntity);
        return new ResponseEntity<>(new ApiResponse<>("success","Document Added successfully",savedTenant.getId()), HttpStatus.OK);
    }

    @PostMapping("/{tenantId}/addresses")
    ResponseEntity<?> addTenantAddress(@PathVariable("tenantId") UUID id, @RequestBody AddressDto request){
        var addressEntity = addressMapper.requestToEntity(request);
        var savedTenant = tenantService.addTenantAddress(id, addressEntity);
        return new ResponseEntity<>(new ApiResponse<>("success","Address Added successfully",savedTenant.getId()), HttpStatus.OK);
    }


}
