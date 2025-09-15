package com.theniche.colivin.domain.service;
import com.theniche.colivin.domain.common.AppCodeGenerator;
import com.theniche.colivin.domain.common.BaseRepository;
import com.theniche.colivin.domain.common.BaseService;
import com.theniche.colivin.domain.entity.Address;
import com.theniche.colivin.domain.entity.Contact;
import com.theniche.colivin.domain.entity.Document;
import com.theniche.colivin.domain.entity.Tenant;
import com.theniche.colivin.domain.exception.BadRequestException;
import com.theniche.colivin.domain.exception.ResourceNotFoundException;
import com.theniche.colivin.domain.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class TenantService extends BaseService<Tenant> {

    private  final TenantRepository repository;

    public TenantService(BaseRepository<Tenant> repository,
                         TenantRepository tenantRepository) {
        super(repository);
        this.repository = tenantRepository;
    }

    @Override
    public Tenant save(Tenant entity) {
        entity.setTenantCode(AppCodeGenerator.generateTenantCode());
        return super.save(entity);
    }

    public Tenant getTenantDetails(UUID id){
      return   repository.findByIdWithDetails(id).orElseThrow(()->new ResourceNotFoundException("Tenant","ID",id));
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

    @Transactional
    public Tenant addTenantDocument(UUID tenantId, Document document){
        // find tenant-by-id
        var existingTenant = repository.findById(tenantId).orElseThrow(()->new ResourceNotFoundException("Tenant","ID",tenantId));
        existingTenant.addTenantDocument(document);
      return   existingTenant;
    }
    @Transactional
    public Tenant addTenantAddress(UUID tenantId, Address address){
        var existingTenant = repository.findById(tenantId).orElseThrow(()->new ResourceNotFoundException("Tenant","ID",tenantId));
        existingTenant.addAddress(address);
        return existingTenant;
    }
    public Tenant addTenantContact(UUID tenantId, Contact contact){
        return null;
    }
}
