package com.theniche.colivin.domain.service;
import com.theniche.colivin.domain.common.AppCodeGenerator;
import com.theniche.colivin.domain.common.BaseRepository;
import com.theniche.colivin.domain.common.BaseService;
import com.theniche.colivin.domain.entity.Tenant;
import com.theniche.colivin.domain.exception.BadRequestException;
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
    public Tenant save(Tenant entity) {
        entity.setTenantCode(AppCodeGenerator.generateTenantCode());
        return super.save(entity);
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
    @Override
    protected void beforeSave(Tenant entity) {
        if(repository.findTenantByEmailAndPhone(entity.getEmail(), entity.getPhone()).isPresent()){
            throw new BadRequestException("The tenant already in the system.Please double check");
        }
        super.beforeSave(entity);
    }
    // findTenantOne by tenant-id,document-id,tenant-phone,tenant-code
}
