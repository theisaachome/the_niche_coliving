package com.theniche.colivin.service;

import java.util.List;
import java.util.UUID;

public interface TenantService {
    void save();
    List<String> findAll();
    String findOne(UUID id);
    void delete(UUID id);
}
