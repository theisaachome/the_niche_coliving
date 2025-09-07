package com.theniche.colivin.service.impl;
import com.theniche.colivin.entity.Tenant;
import com.theniche.colivin.exception.ResourceNotFoundException;
import com.theniche.colivin.mapper.DataMapper;
import com.theniche.colivin.mapper.IMapper;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.address.AddressDto;
import com.theniche.colivin.payload.tenant.TenantRequestDto;
import com.theniche.colivin.payload.tenant.TenantResponseDto;
import com.theniche.colivin.repository.TenantRepository;
import com.theniche.colivin.service.AddressService;
import com.theniche.colivin.service.TenantService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final AddressService addressService;

    private final IMapper<TenantRequestDto, TenantResponseDto, Tenant> dataMapper;


    public TenantServiceImpl(TenantRepository tenantRepository, AddressService addressService,
                             @Qualifier("tenantMapper") IMapper dataMapper) {
        this.tenantRepository = tenantRepository;
        this.addressService = addressService;
        this.dataMapper = dataMapper;
    }

    @Override
    public ApiResponse<ResponseData> createNewTenant(TenantRequestDto dto) {
        var entity = dataMapper.mapToEntity(dto);
        var savedEntity = tenantRepository.save(entity);
        return new ApiResponse<>("success",
        "createNewTenant operation successfully",
        new ResponseData(savedEntity.getId(),
                savedEntity.getFullName(),
                savedEntity.getCreatedDate(),
                savedEntity.getCreatedBy()));
    }

    @Override
    public ApiResponse<ResponseData> updateTenant(UUID tenantId, TenantRequestDto dto) {
        var exisitingTenant  = tenantRepository.findById(tenantId)
                .orElseThrow(()->new ResourceNotFoundException("Tenant","ID",tenantId));
        exisitingTenant.setGender(dto.gender());
        exisitingTenant.setDateOfBirth(dto.dateOfBirth());
        exisitingTenant.setFullName(dto.fullName());
        tenantRepository.save(exisitingTenant);
        return   new ApiResponse<>("success",
                "createNewTenant operation successfully",
                new ResponseData(exisitingTenant.getId(), exisitingTenant.getFullName(),
                exisitingTenant.getCreatedDate(),exisitingTenant.getCreatedBy()));

    }

    @Override
    public ApiResponse<TenantResponseDto> getTenantDetails(UUID tenantId) {
        var exisitingTenant  = tenantRepository.findById(tenantId)
                .orElseThrow(()->new ResourceNotFoundException("Tenant","ID",tenantId));
        return new ApiResponse<>("success",
                "createNewTenant operation successfully",
                dataMapper.mapToDto(exisitingTenant));
    }

    @Override
    public ApiResponse<ResponseData> deleteTenant(UUID tenantId) {

        var exisitingTenant  = tenantRepository.findById(tenantId)
                .orElseThrow(()->new ResourceNotFoundException("Tenant","ID",tenantId));
        exisitingTenant.setDeleted(true);
        return   new ApiResponse<>("success",
                "createNewTenant operation successfully",
                new ResponseData(exisitingTenant.getId(), exisitingTenant.getFullName(),
                exisitingTenant.getCreatedDate(),exisitingTenant.getCreatedBy()));
    }
    @Override
    public ApiResponse<ResponseData> addTenantAddress(UUID tenantId, AddressDto dto) {
        var result = addressService.addAddress(tenantId, dto);
        return result;
    }
}
