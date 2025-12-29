package com.theniche.colivin.tenants;
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

}
