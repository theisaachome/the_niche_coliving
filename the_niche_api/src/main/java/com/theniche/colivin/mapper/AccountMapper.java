package com.theniche.colivin.mapper;

import com.theniche.colivin.payload.account.AccountRequest;
import com.theniche.colivin.payload.account.AccountResponse;
import com.theniche.core.domain.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements IMapper<AccountRequest, AccountResponse, Account> {
    @Override
    public Account mapToEntity(AccountRequest dto) {
        return new  Account(dto.accountId());
    }

    @Override
    public AccountResponse mapToDto(Account entity) {
        return new AccountResponse(entity.getAccountId(),entity.getCreatedDate(),entity.getCreatedBy());
    }
}
