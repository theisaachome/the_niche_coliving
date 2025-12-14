package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.entity.Account;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService extends BaseService<Account> {
    public AccountService(BaseRepository<Account> repository) {
        super(repository);
    }

    @Override
    public Account update(UUID id, Account entity) {
        return null;
    }
}
