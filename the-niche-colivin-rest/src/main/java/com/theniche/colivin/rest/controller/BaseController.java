package com.theniche.colivin.rest.controller;


import com.theniche.colivin.domain.entity.BaseEntity;
import com.theniche.colivin.domain.common.BaseService;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.BaseResponseDto;
import com.theniche.colivin.rest.mapper.BaseMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

public abstract class BaseController< E extends BaseEntity, RQ,RS > {
    protected final BaseService<E> service;
    protected final BaseMapper<E, RQ,RS> mapper;

    public BaseController(BaseService<E> service, BaseMapper<E, RQ, RS> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

//    @GetMapping
    public ResponseEntity<List<RS>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<E> entities = service.findAll(pageable);

        List<RS> responses = mapper.mapList(entities.getContent(),mapper::entityToResponse);
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<RS> create(@Valid @RequestBody RQ requestDto) {
        E entity = mapper.requestToEntity(requestDto);
        E savedEntity = service.save(entity);
        RS response = mapper.entityToResponse(savedEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RS> getDetailsById(@PathVariable("id") UUID id) {
        var result = service.findById(id);
        return ResponseEntity.ok(mapper.entityToResponse(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>>  deleteById(@PathVariable("id") UUID id) {
        var result = service.deleteById(id);
        return  new ResponseEntity<>(new ApiResponse<>("success","content deleted",result.getId().toString()),HttpStatus.OK);
    }

    protected  abstract  <D> ResponseEntity<D>  update (@PathVariable UUID id, @Valid @RequestBody RQ requestDto);
}
