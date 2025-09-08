package com.theniche.colivin.rest.dto.account;

import com.theniche.colivin.rest.dto.BaseRequestDto;


public class AccountRequest extends BaseRequestDto {
    private String accountId;
    private String name;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
