package com.praisesystem.backend.accounts.impl;

import com.praisesystem.backend.accounts.AccountDto;
import com.praisesystem.backend.accounts.AccountRepository;
import com.praisesystem.backend.accounts.AccountTransactionalService;
import com.praisesystem.backend.accounts.enums.PlatformType;
import com.praisesystem.backend.accounts.mapper.AccountMapper;
import com.praisesystem.backend.accounts.model.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountTransactionalServiceImpl implements AccountTransactionalService {

    AccountRepository accountRepository;
    AccountMapper accountMapper;

    public Account createOrUpdateAccount(AccountDto dto) {
        Account account = accountRepository.findByAccountIdAndPlatform(dto.getAccountId(), dto.getPlatform());

        account = account == null ? accountMapper.toNewAccount(dto) : accountMapper.updateAccount(account, dto);

        return accountRepository.save(account);
    }

    @Override
    public List<Account> createOrUpdateAccounts(List<AccountDto> dtos) {
        List<String> ids = dtos.stream().map(AccountDto::getAccountId).collect(Collectors.toList());
        PlatformType platformType = dtos.get(0).getPlatform();

        Map<String, Account> existingAccounts = accountRepository.findByAccountIdInAndPlatform(ids, platformType)
                .stream()
                .collect(Collectors.toMap(Account::getAccountId, a -> a));

        List<Account> accounts = dtos.stream()
                .map(dto -> {
                    if (existingAccounts.containsKey(dto.getAccountId())) {
                        return accountMapper.updateAccount(existingAccounts.get(dto.getAccountId()), dto);
                    }
                    return accountMapper.toNewAccount(dto);
                }).collect(Collectors.toList());
        return accountRepository.saveAll(accounts);
    }
}
