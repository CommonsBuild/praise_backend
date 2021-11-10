package com.praisesystem.backend.accounts;

import com.praisesystem.backend.accounts.model.Account;

import java.util.List;

public interface AccountService {
    Account createOrUpdateAccount(AccountDto dto);
    List<Account> createOrUpdateAccounts(List<AccountDto> dtos);
}
