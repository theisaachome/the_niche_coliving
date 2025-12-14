package com.theniche.colivin.tenants;

import com.theniche.colivin.common.payload.ApiResponse;
import com.theniche.colivin.tenants.dto.TenantRequest;
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

}
