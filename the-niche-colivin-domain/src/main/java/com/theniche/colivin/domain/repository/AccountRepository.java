package com.theniche.colivin.domain.repository;

import com.theniche.colivin.domain.entity.Account;

import java.util.List;

public interface AccountRepository extends BaseRepository<Account> {
    List<Account> findByName(String name);
}
