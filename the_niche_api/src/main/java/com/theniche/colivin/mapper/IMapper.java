package com.theniche.colivin.mapper;

public interface IMapper <I,O,E>{
    E mapToEntity(I i);
    O mapToDto(E e);
}
