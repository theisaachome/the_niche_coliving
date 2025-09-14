package com.theniche.colivin.domain.common;
import com.theniche.colivin.domain.entity.BaseEntity;
import com.theniche.colivin.domain.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

public abstract class BaseService <T extends BaseEntity> {
    protected final BaseRepository<T> repository;
    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public T save(T entity){
        return repository.save(entity);
    }

    @Transactional
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
        var savedEntity=repository.save(entity);
        return savedEntity;
    }

    @Transactional
    public abstract  T update(UUID id, T entity);

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
