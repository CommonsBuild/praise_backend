package com.praisesystem.backend.accounts.impl;

import com.praisesystem.backend.accounts.dto.AccountDto;
import com.praisesystem.backend.accounts.services.AccountService;
import com.praisesystem.backend.accounts.services.AccountTransactionalService;
import com.praisesystem.backend.accounts.model.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {

    AccountTransactionalService accountTransactionalService;

    @Override
    public Account createOrUpdateAccount(AccountDto dto) {
        return accountTransactionalService.createOrUpdateAccount(dto);
    }

    @Override
    public List<Account> createOrUpdateAccounts(List<AccountDto> dtos) {
        return accountTransactionalService.createOrUpdateAccounts(dtos);
    }
}
