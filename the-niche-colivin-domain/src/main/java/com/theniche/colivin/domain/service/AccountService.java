package com.theniche.colivin.domain.service;

import com.theniche.colivin.domain.entity.Account;
import com.theniche.colivin.domain.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account> {
    public AccountService(BaseRepository<Account> repository) {
        super(repository);
    }
}
