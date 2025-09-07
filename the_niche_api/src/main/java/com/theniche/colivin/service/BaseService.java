package com.theniche.colivin.service;

import java.util.List;

public interface BaseService<Req,Res,ID> {
    Res create(Req dto);
    Res update(ID id, Req dto);
    Res findById(ID id);
    void delete(ID id);
    <T> List<T> search();
}
