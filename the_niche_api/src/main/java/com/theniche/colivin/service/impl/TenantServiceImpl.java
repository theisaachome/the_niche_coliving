package com.theniche.colivin.service.impl;
import com.theniche.colivin.exception.ResourceNotFoundException;
import com.theniche.colivin.mapper.DataMapper;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.tenant.TenantRequestDto;
import com.theniche.colivin.payload.tenant.TenantResponseDto;
import com.theniche.colivin.repository.TenantRepository;
import com.theniche.colivin.service.TenantService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final DataMapper dataMapper;

    public TenantServiceImpl(TenantRepository tenantRepository, DataMapper dataMapper) {
        this.tenantRepository = tenantRepository;
        this.dataMapper = dataMapper;
    }

    @Override
    public ApiResponse<ResponseData> createNewTenant(TenantRequestDto dto) {
        var entity = dataMapper.mapToTenantEntity(dto);
        var savedEntity = tenantRepository.save(entity);
        return new ApiResponse<>("success",
        new ResponseData(savedEntity.getId(), savedEntity.getFullName(), savedEntity.getCreatedDate(),savedEntity.getCreatedBy()));
    }

    @Override
    public ApiResponse<ResponseData> updateTenant(UUID tenantId, TenantRequestDto dto) {
        var exisitingTenant  = tenantRepository.findById(tenantId)
                .orElseThrow(()->new ResourceNotFoundException("Tenant","ID",tenantId));
        exisitingTenant.setGender(dto.gender());
        exisitingTenant.setDateOfBirth(dto.dateOfBirth());
        exisitingTenant.setFullName(dto.fullName());
        tenantRepository.save(exisitingTenant);
        return   new ApiResponse<>("success"
                ,new ResponseData(exisitingTenant.getId(), exisitingTenant.getFullName(),
                exisitingTenant.getCreatedDate(),exisitingTenant.getCreatedBy()));

    }

    @Override
    public ApiResponse<TenantResponseDto> getTenantDetails(UUID tenantId) {
        var exisitingTenant  = tenantRepository.findById(tenantId)
                .orElseThrow(()->new ResourceNotFoundException("Tenant","ID",tenantId));
        return new ApiResponse<>("success",
                dataMapper.mapToTenantResponseDto(exisitingTenant));
    }

    @Override
    public ApiResponse<ResponseData> deleteTenant(UUID tenantId) {

        var exisitingTenant  = tenantRepository.findById(tenantId)
                .orElseThrow(()->new ResourceNotFoundException("Tenant","ID",tenantId));
        exisitingTenant.setDeleted(true);
        return   new ApiResponse<>("success"
                ,new ResponseData(exisitingTenant.getId(), exisitingTenant.getFullName(),
                exisitingTenant.getCreatedDate(),exisitingTenant.getCreatedBy()));
    }
}
