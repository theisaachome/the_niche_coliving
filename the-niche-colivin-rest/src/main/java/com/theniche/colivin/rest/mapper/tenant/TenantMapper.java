package com.theniche.colivin.rest.mapper.tenant;

import com.theniche.colivin.domain.entity.Tenant;
import com.theniche.colivin.rest.dto.tenant.TenantRequest;
import com.theniche.colivin.rest.dto.tenant.TenantResponse;
import com.theniche.colivin.rest.mapper.BaseMapper;
import com.theniche.colivin.rest.mapper.address.AddressMapper;
import com.theniche.colivin.rest.mapper.doucment.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class TenantMapper implements BaseMapper<Tenant, TenantRequest, TenantResponse> {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public Tenant requestToEntity(TenantRequest requestDto) {
        var tenant= Tenant.builder()
                .fullName(requestDto.fullName())
                .email(requestDto.email())
                .phone(requestDto.phone())
                .dateOfBirth(requestDto.dateOfBirth())
                .gender(requestDto.gender())
                .build();
        if(requestDto.addresses() != null && !requestDto.addresses().isEmpty()) {
            var addresses =  requestDto.addresses().stream().map(addressMapper::requestToEntity).collect(Collectors.toSet());
                      addresses.forEach(tenant::addAddress);
        }
        if(requestDto.documents() != null && !requestDto.documents().isEmpty()) {
          var documents=  requestDto.documents().stream().map(documentMapper::requestToEntity).collect(Collectors.toSet());
                    documents.forEach(tenant::addTenantDocument);
        }
        return tenant;
    }

    @Override
    public TenantResponse entityToResponse(Tenant entity) {
//        var addresses = entity.getAddresses().stream().map(addressMapper::entityToResponse).collect(Collectors.toSet());
//        var documents = entity.getDocuments().stream().map(documentMapper::entityToResponse).collect(Collectors.toSet());
        return new TenantResponse(
                entity.getId(),
                entity.getTenantCode(),
                entity.getFullName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getGender(),
                entity.getDateOfBirth(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
        );
    }
}
