package com.praisesystem.backend.accounts;

import com.praisesystem.backend.accounts.model.Account;

import java.util.List;

public interface AccountTransactionalService {
    Account createOrUpdateAccount(AccountDto dto);

    List<Account> createOrUpdateAccounts(List<AccountDto> dtos);
}
