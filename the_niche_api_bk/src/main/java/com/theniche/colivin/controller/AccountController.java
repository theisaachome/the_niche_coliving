package com.theniche.colivin.controller;

import com.theniche.colivin.mapper.AccountMapper;
import com.theniche.colivin.payload.account.AccountRequest;
import com.theniche.colivin.payload.account.AccountResponse;
import com.theniche.core.domain.entity.Account;
import com.theniche.core.domain.service.AccountService;
import com.theniche.core.domain.service.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;
    private final AccountMapper accountMapper;

    public AccountController(AccountServiceImpl accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest account) {
        var accountEntity = accountMapper.mapToEntity(account);
        var result = accountService.save(accountEntity);
        return new ResponseEntity<>(accountMapper.mapToDto(result), HttpStatus.OK) ;
    }

}
