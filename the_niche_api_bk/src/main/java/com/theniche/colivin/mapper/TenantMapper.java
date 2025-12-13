package com.theniche.colivin.mapper;


import com.theniche.colivin.entity.Tenant;
import com.theniche.colivin.payload.tenant.TenantRequestDto;
import com.theniche.colivin.payload.tenant.TenantResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper implements IMapper<TenantRequestDto,TenantResponseDto,Tenant> {
    @Override
    public Tenant mapToEntity(TenantRequestDto dto) {
        return Tenant.builder()
                .fullName(dto.fullName())
                .phone(dto.phone())
                .email(dto.email())
                .gender(dto.gender())
                .dateOfBirth(dto.dateOfBirth())
                .build();
    }

    @Override
    public TenantResponseDto mapToDto(Tenant entity) {
        return  new TenantResponseDto(
                entity.getId(),
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
