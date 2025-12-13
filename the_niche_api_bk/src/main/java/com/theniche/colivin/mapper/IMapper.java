package com.theniche.colivin.mapper;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface IMapper <I,O,E>{
    E mapToEntity(I i);
    O mapToDto(E e);

    default  <S,R> List<R> mapList(List<S> source, Function<? super S,? extends  R > mapper){
        Objects.requireNonNull(source);
        return source == null? List.of() : source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }
}
