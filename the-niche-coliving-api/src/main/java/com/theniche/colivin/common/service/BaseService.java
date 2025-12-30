package com.theniche.colivin.common.service;

import com.theniche.colivin.common.domain.BaseEntity;
import com.theniche.colivin.common.domain.EntityStatus;
import com.theniche.colivin.common.exception.ResourceNotFoundException;
import com.theniche.colivin.common.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseService  {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
   /*

    protected final BaseRepository<T> repository;
    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public T save(T entity){
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public T findById(UUID id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Entity","ID",id));
    }

    // soft-delete
    @Transactional
    public T deleteById(UUID id){
        var entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Entity","ID",id));
//        repository.delete(entity);
        entity.setDeleted(true);
        entity.setStatus(EntityStatus.INACTIVE);
        var savedEntity=repository.save(entity);
        return savedEntity;
    }

    @Transactional
    public abstract  T update(UUID id, T entity);

//    @Transactional
//    public T update(T entity) {
//        if (entity.getId() == null || !repository.existsById(entity.getId())) {
//            throw new EntityNotFoundException("Entity not found for update");
//        }
//        return repository.save(entity);
//    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<T> searchList(Specification<T> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public Optional<T> searchOne(Specification<T> spec) {
        return repository.findOne(spec);
    }

    @Transactional(readOnly = true)
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    // Hook methods for custom validation/logic
    protected void beforeSave(T entity) {
        // Override in subclasses if needed
    }

    protected void afterSave(T entity) {
        // Override in subclasses if needed
    }

    protected void beforeUpdate(T entity) {
        // Override in subclasses if needed
    }

    protected void afterUpdate(T entity) {
        // Override in subclasses if needed
    }

    protected void beforeDelete(UUID id) {
        // Override in subclasses if needed
    }

    protected void afterDelete(UUID id) {
        // Override in subclasses if needed
    }

    */
}
