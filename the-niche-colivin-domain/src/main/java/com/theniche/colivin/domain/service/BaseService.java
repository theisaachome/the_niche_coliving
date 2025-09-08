package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.entity.BaseEntity;
import com.theniche.colivin.domain.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService <T extends BaseEntity> {
    protected final BaseRepository<T> repository;
    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public T save(T entity){
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
