package com.theniche.colivin.rest.mapper.account;

import com.theniche.colivin.domain.entity.Account;
import com.theniche.colivin.rest.dto.account.AccountRequest;
import com.theniche.colivin.rest.dto.account.AccountResponse;
import com.theniche.colivin.rest.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper extends BaseMapper<Account, AccountRequest, AccountResponse> {

    @Override
    Account requestToEntity(AccountRequest requestDto);

    @Override
    List<AccountResponse> entitiesToResponses(List<Account> entities);
}
