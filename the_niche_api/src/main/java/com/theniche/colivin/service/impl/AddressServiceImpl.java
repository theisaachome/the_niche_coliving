package com.theniche.colivin.service.impl;
import com.theniche.colivin.entity.Address;
import com.theniche.colivin.exception.ResourceNotFoundException;
import com.theniche.colivin.mapper.AddressMapper;
import com.theniche.colivin.mapper.IMapper;
import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.BaseResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.SuccessResponse;
import com.theniche.colivin.payload.address.AddressDto;
import com.theniche.colivin.repository.AddressRepository;
import com.theniche.colivin.repository.TenantRepository;
import com.theniche.colivin.service.AddressService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/*
@Service
public class AddressServiceImpl implements AddressService<BaseResponse> {

    private final AddressRepository addressRepository;
    private final IMapper<AddressDto,AddressDto, Address> addressMapper;
    private final TenantRepository tenantRepository;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, TenantRepository tenantRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public ApiResponse<ResponseData> addAddress(UUID tenantId, AddressDto dto) {
        var tenant = tenantRepository.findById(tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant","ID",tenantId));
        var addressEntity = addressMapper.mapToEntity(dto);
        addressEntity.setTenant(tenant);
        var savedAddress = addressRepository.save(addressEntity);
        return new ApiResponse<>("success",
                "addAddress operation successful",
                new ResponseData(savedAddress.getId(),
                        savedAddress.getAddressLine1(),
                        savedAddress.getCreatedDate(),
                        savedAddress.getCreatedBy())
                );
    }

    @Override
    public ApiResponse<ResponseData> updateAddress(UUID addressId, AddressDto dto) {
        var existingAddress = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address","ID",addressId));
        existingAddress.setAddressLine1(dto.addressLine1());
        existingAddress.setAddressLine2(dto.addressLine2());
        existingAddress.setCity(dto.city());
        existingAddress.setPostalCode(dto.postalCode());
        existingAddress.setState(dto.state());
        existingAddress.setCountry(dto.country());
        var updatedAddress = addressRepository.save(existingAddress);
        return new ApiResponse<>("success",
                "updateAddress operation successful",
                new ResponseData(updatedAddress.getId(),
                        updatedAddress.getAddressLine1(),
                        updatedAddress.getUpdatedDate(),
                        updatedAddress.getUpdatedBy()));
    }

    @Override
    public ApiResponse<ResponseData> deleteAddress(UUID addressId) {
        var existingAddress = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address","ID",addressId));
        existingAddress.setDeleted(true);
        var updatedAddress = addressRepository.save(existingAddress);
        return new ApiResponse<>("success",
                "deleteAddress operation successful",
                new ResponseData(updatedAddress.getId(),
                        updatedAddress.getAddressLine1(),
                        updatedAddress.getUpdatedDate(),
                        updatedAddress.getUpdatedBy()));
    }

    @Override
    public ApiResponse<AddressDto> getAddressDetailsById(UUID addressId) {
        var existingAddress = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address","ID",addressId));
        return new ApiResponse<>("success",
                "getAddressDetailsById operation successful.",
                addressMapper.mapToDto(existingAddress));
    }

//    @Override
//    public ApiResponse<List<AddressDto>> getAllAddressesByTenantId(UUID tenantId) {
//        var addressList = addressRepository.findByTenantId(tenantId);
//        return new ApiResponse<>("success",
//                "getAddressDetailsById operation successful.",
//                addressMapper.mapList(addressList,addressMapper::mapToDto) );
//    }

    @Override
    public ApiResponse<SuccessResponse> create(AddressDto dto) {return  null;}

    @Override
    public ApiResponse<SuccessResponse> update(UUID uuid, AddressDto dto) {
        return null;
    }

    @Override
    public ApiResponse<SuccessResponse> findById(UUID uuid) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {
        var existingAddress = addressRepository.findById(uuid)
                .orElseThrow(()-> new ResourceNotFoundException("Address","ID",uuid));
        existingAddress.setDeleted(true);
        addressRepository.save(existingAddress);
    }

    @Override
    public <T> List<T> search() {
        return List.of();
    }

    @Override
    public ApiResponse<AddressDto> testMethod() {
        return null;
    }
}

 */
