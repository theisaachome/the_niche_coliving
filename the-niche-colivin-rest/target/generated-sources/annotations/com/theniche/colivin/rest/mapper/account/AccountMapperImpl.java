package com.theniche.colivin.rest.mapper.account;

import com.theniche.colivin.domain.entity.Account;
import com.theniche.colivin.rest.dto.account.AccountRequest;
import com.theniche.colivin.rest.dto.account.AccountResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-09T00:10:33+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountResponse entityToResponse(Account entity) {
        if ( entity == null ) {
            return null;
        }

        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setId( entity.getId() );
        accountResponse.setCreatedBy( entity.getCreatedBy() );
        accountResponse.setUpdatedBy( entity.getUpdatedBy() );
        accountResponse.setVersion( entity.getVersion() );
        accountResponse.setAccountId( entity.getAccountId() );
        accountResponse.setName( entity.getName() );

        return accountResponse;
    }

    @Override
    public Account requestToEntity(AccountRequest requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setAccountId( requestDto.getAccountId() );
        account.setName( requestDto.getName() );

        return account;
    }

    @Override
    public List<AccountResponse> entitiesToResponses(List<Account> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AccountResponse> list = new ArrayList<AccountResponse>( entities.size() );
        for ( Account account : entities ) {
            list.add( entityToResponse( account ) );
        }

        return list;
    }
}
