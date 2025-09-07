package com.theniche.colivin.service;

import com.theniche.colivin.payload.ApiResponse;
import com.theniche.colivin.payload.BaseResponse;
import com.theniche.colivin.payload.ResponseData;
import com.theniche.colivin.payload.address.AddressDto;

import java.util.List;
import java.util.UUID;

public interface AddressService<T> extends BaseService<AddressDto,ApiResponse<? extends BaseResponse>,UUID>{

    ApiResponse<ResponseData>  addAddress(UUID tenantId, AddressDto dto);
    ApiResponse<ResponseData> updateAddress(UUID addressId, AddressDto address);
    ApiResponse<ResponseData> deleteAddress(UUID addressId);
    ApiResponse<AddressDto> getAddressDetailsById(UUID addressId);
    ApiResponse<List<AddressDto>> getAllAddressesByTenantId(UUID tenantId);

    ApiResponse<AddressDto> testMethod();
}
