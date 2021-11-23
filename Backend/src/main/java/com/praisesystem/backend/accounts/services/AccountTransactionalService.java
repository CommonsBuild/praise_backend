package com.praisesystem.backend.accounts.services;

import com.praisesystem.backend.accounts.dto.AccountDto;
import com.praisesystem.backend.accounts.model.Account;

import java.util.List;

public interface AccountTransactionalService {
    Account createOrUpdateAccount(AccountDto dto);

    List<Account> createOrUpdateAccounts(List<AccountDto> dtos);
}
