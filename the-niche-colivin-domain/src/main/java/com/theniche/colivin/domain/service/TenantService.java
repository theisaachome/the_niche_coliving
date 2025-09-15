package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.common.BaseRepository;
import com.theniche.colivin.domain.common.BaseService;
import com.theniche.colivin.domain.entity.Tenant;
import com.theniche.colivin.domain.exception.ResourceNotFoundException;
import com.theniche.colivin.domain.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TenantService extends BaseService<Tenant> {

    private  final TenantRepository repository;

    public TenantService(BaseRepository<Tenant> repository, TenantRepository repository1) {
        super(repository);
        this.repository = repository1;
    }

    @Override
    public Tenant update(UUID id, Tenant entity) {
        var existingEntity = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Tenant","id",id));
        existingEntity.setFullName(entity.getFullName());
        existingEntity.setEmail(entity.getEmail());
        existingEntity.setPhone(entity.getPhone());
        existingEntity.setDateOfBirth(entity.getDateOfBirth());
        return repository.save(existingEntity);
    }

    // findTenantOne by tenant-id,document-id,tenant-phone,tenant-code
}
