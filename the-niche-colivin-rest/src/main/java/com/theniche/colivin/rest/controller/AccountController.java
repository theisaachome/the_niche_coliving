package com.theniche.colivin.rest.controller;

import com.theniche.colivin.domain.entity.Account;
import com.theniche.colivin.domain.service.AccountService;
import com.theniche.colivin.rest.dto.account.AccountRequest;
import com.theniche.colivin.rest.dto.account.AccountResponse;
import com.theniche.colivin.rest.mapper.account.AccountMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController extends BaseController<Account, AccountRequest, AccountResponse>{
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        super(accountService,accountMapper);
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
}
