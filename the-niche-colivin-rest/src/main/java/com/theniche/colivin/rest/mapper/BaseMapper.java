package com.theniche.colivin.rest.mapper;
import com.theniche.colivin.domain.entity.BaseEntity;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface BaseMapper<E extends BaseEntity, RQ,RS> {
    E requestToEntity(RQ requestDto);
    RS entityToResponse(E entity);

    default  <S,R> List<R> mapList(List<S> source, Function<? super S,? extends  R > mapper){
        Objects.requireNonNull(source);
        return source == null? List.of() : source.stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }
//    List<RS> entitiesToResponses(List<E> entities);

//    void updateEntityFromRequest(RQ requestDto, E entity);
}
