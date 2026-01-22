package com.theniche.colivin.tenants;

import com.theniche.colivin.common.aop.annotation.Audit;
import com.theniche.colivin.common.domain.EntityStatus;
import com.theniche.colivin.common.domain.GenericSpecification;
import com.theniche.colivin.common.domain.SearchCriteria;
import com.theniche.colivin.common.domain.TenantStatus;
import com.theniche.colivin.common.exception.ResourceNotFoundException;
import com.theniche.colivin.common.payload.PageApiResponse;
import com.theniche.colivin.common.payload.PageRequestDto;
import com.theniche.colivin.common.service.BaseService;
import com.theniche.colivin.tenants.dto.TenantDetailResponse;
import com.theniche.colivin.tenants.dto.TenantRequest;
import com.theniche.colivin.tenants.dto.TenantResponse;
import com.theniche.colivin.tenants.dto.TenantSearchFilters;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TenantServiceImpl extends BaseService implements TenantService {

    private final TenantMapper tenantMapper;
    private final TenantRepository tenantRepository;
    public TenantServiceImpl(TenantMapper tenantMapper, TenantRepository tenantRepository) {
        this.tenantMapper = tenantMapper;
        this.tenantRepository = tenantRepository;
    }

    @Audit(action = "tenant.registerNewTenant",logArgs = true)
    @Override
    public TenantResponse registerNewTenant(TenantRequest dto) {
        var newTenant = tenantMapper.toEntity(dto);
       var savedTenant=   tenantRepository.save(newTenant);
        return tenantMapper.toResponse(savedTenant);
    }

    @Override
    public TenantResponse updateTenant(UUID id, TenantRequest dto) {
        var existingTenant = tenantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant","ID",id));

        existingTenant.setFullName(dto.fullName());
        existingTenant.setEmail(dto.email());
        existingTenant.setPhone(dto.phone());
        existingTenant.setGender(dto.gender());
        existingTenant.setDateOfBirth(existingTenant.getDateOfBirth());

       var updatedTenant= tenantRepository.save(existingTenant);
        return tenantMapper.toResponse(updatedTenant);
    }

    @Override
    public TenantDetailResponse getTenant(UUID id) {
        var foundTenant = tenantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant","ID",id));
        return tenantMapper.toTenantDetailsResponse(foundTenant);
    }

    @Override
    public void deleteTenant(UUID id) {
        // soft-deleted which can mean checkout / move out
        var existingTenant = tenantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant","ID",id));
        existingTenant.setTenantStatus(TenantStatus.INACTIVE);
        tenantRepository.save(existingTenant);
    }
    // hard-delete-by admin
    public void hardDeleteOperation(UUID id) {
        var existingTenant = tenantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant","ID",id));
        tenantRepository.delete(existingTenant);
    }
    @Override
    public List<TenantResponse> getTenants() {
        return tenantRepository.findAll().stream()
                .map(tenantMapper::toResponse)
                .collect(Collectors.toList());
    }

    // search tenant with param
    @Override
    @Audit(action="tenant.search")
    public PageApiResponse<TenantResponse> searchTenant(TenantSearchFilters filters, PageRequestDto pageRequest) {
        // build specification
        GenericSpecification<Tenant> specBuilder = new GenericSpecification<>();
        addIfHasText(specBuilder, "tenantCode", filters.getTenantCode(), SearchCriteria.Operation.EQUAL);
        addIfHasText(specBuilder, "email", filters.getEmail(), SearchCriteria.Operation.EQUAL);
        addIfHasText(specBuilder, "phone", filters.getPhone(), SearchCriteria.Operation.EQUAL);
        addIfHasText(specBuilder, "fullName", filters.getFullName(), SearchCriteria.Operation.LIKE);

        log.info("Search filters count: {}", specBuilder.getCriteriaCount());

        var pagedResults = tenantRepository.findAll(specBuilder.build(),pageRequest.toPageable());
        var pageToList = pagedResults.getContent()
                .stream().map(tenantMapper::toResponse).collect(Collectors.toList());
        return new PageApiResponse<>(
                pageToList,
                pagedResults.getNumber(),
                pagedResults.getSize(),
                pagedResults.getTotalElements(),
                pagedResults.getTotalPages(),
                pagedResults.isLast()
        );
    }


    private void addIfHasText( GenericSpecification<Tenant> spec,
                               String field,
                               String value,
                               SearchCriteria.Operation operation){
            if(StringUtils.hasText(value)){
                spec.add(new SearchCriteria(field,value,operation));
            }
    }
}
