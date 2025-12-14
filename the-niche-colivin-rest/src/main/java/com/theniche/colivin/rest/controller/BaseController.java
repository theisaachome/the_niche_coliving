package com.theniche.colivin.rest.controller;
import com.theniche.colivin.domain.entity.BaseEntity;
import com.theniche.colivin.rest.ApiResponse;
import com.theniche.colivin.rest.dto.PageApiResponse;
import com.theniche.colivin.rest.mapper.BaseMapper;
import com.theniche.colivin.rest.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class BaseController< E extends BaseEntity, RQ,RS > {
    protected final BaseService<E> service;
    protected final BaseMapper<E, RQ,RS> mapper;

    public BaseController(BaseService<E> service, BaseMapper<E, RQ, RS> mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @GetMapping
    public   ResponseEntity<PageApiResponse> getAllPagedResponse(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir
    ){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        var entities = service.findAll(pageable);
        var pagedList = entities.getContent().stream().map(mapper::entityToResponse).collect(Collectors.toList());
        var result= new PageApiResponse(
                pagedList,
                entities.getNumber(),
                entities.getSize(),
                entities.getTotalElements(),
                entities.getTotalPages(),
                entities.isLast()
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
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
