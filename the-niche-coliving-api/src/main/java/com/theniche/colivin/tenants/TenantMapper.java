package com.theniche.colivin.tenants;
import com.theniche.colivin.tenants.dto.TenantDetailResponse;
import com.theniche.colivin.tenants.dto.TenantRequest;
import com.theniche.colivin.tenants.dto.TenantResponse;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper {

     Tenant toEntity(TenantRequest request){
        var tenant = new  Tenant()
                .setFullName(request.fullName())
                .setEmail(request.email())
                .setPhone(request.phone())
                .setGender(request.gender());

        return tenant;
    }

    TenantResponse toResponse(Tenant entity){
         return new TenantResponse(
                 entity.getId().toString(),
                 entity.getTenantCode(),
                 entity.getFullName(),
                 entity.getEmail(),
                 entity.getPhone(),
                 entity.getStatus().toString()
         );
    }

    TenantDetailResponse toTenantDetailsResponse(Tenant entity){
         return new TenantDetailResponse(
                 entity.getId(),
                 entity.getTenantCode(),
                 entity.getFullName(),
                 entity.getEmail(),
                 entity.getPhone(),
                 entity.getTenantStatus(),
                 entity.isDeleted(),
                 entity.getCreatedBy(),
                 entity.getUpdatedBy(),
                 entity.getCreatedDate(),
                 entity.getUpdatedDate()
         );
    }

}
