package com.theniche.colivin.tenants;

import com.theniche.colivin.common.domain.EntityStatus;
import com.theniche.colivin.common.domain.TenantStatus;
import com.theniche.colivin.common.exception.ResourceNotFoundException;
import com.theniche.colivin.tenants.dto.TenantRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TenantServiceImpl  implements TenantService {

    private final TenantMapper tenantMapper;
    private final TenantRepository tenantRepository;
    public TenantServiceImpl(TenantMapper tenantMapper, TenantRepository tenantRepository) {
        this.tenantMapper = tenantMapper;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant registerNewTenant(TenantRequest dto) {
        var newTenant = tenantMapper.toEntity(dto);
       var savedTenant=   tenantRepository.save(newTenant);
        return savedTenant;
    }

    @Override
    public Tenant updateTenant(UUID id, TenantRequest dto) {
        var existingTenant = tenantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant","ID",id));

        existingTenant.setFullName(dto.fullName());
        existingTenant.setEmail(dto.email());
        existingTenant.setPhone(dto.phone());
        existingTenant.setGender(dto.gender());
        existingTenant.setDateOfBirth(existingTenant.getDateOfBirth());
        return null;
    }

    @Override
    public Tenant getTenant(UUID id) {

        return tenantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant","ID",id));
    }

    @Override
    public void deleteTenant(UUID id) {
        // soft-deleted which can mean checkout / move out
        var existingTenant = tenantRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant","ID",id));
        existingTenant.setStatus(EntityStatus.INACTIVE);
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
    public List<Tenant> getTenants() {
        return tenantRepository.findAll();
    }
}
