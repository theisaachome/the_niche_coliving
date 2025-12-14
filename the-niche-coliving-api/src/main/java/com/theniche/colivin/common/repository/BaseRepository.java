package com.theniche.colivin.common.repository;


import com.theniche.colivin.common.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BaseRepository <T extends BaseEntity>
        extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {
    @Override
    Page<T> findAll(Specification<T> spec, Pageable pageable);
}
