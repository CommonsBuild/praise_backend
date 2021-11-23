package com.praisesystem.backend.accounts.impl;

import com.praisesystem.backend.accounts.dto.AccountDto;
import com.praisesystem.backend.accounts.AccountRepository;
import com.praisesystem.backend.accounts.services.AccountTransactionalService;
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
        Account account = accountRepository.findByIdAndPlatform(dto.getId(), dto.getPlatform());

        account = account == null ? accountMapper.toNewAccount(dto) : accountMapper.updateAccount(account, dto);

        return accountRepository.save(account);
    }

    @Override
    public List<Account> createOrUpdateAccounts(List<AccountDto> dtoList) {
        List<String> ids = dtoList.stream().map(AccountDto::getId).collect(Collectors.toList());
        PlatformType platformType = dtoList.get(0).getPlatform();

        Map<String, Account> existingAccounts = accountRepository.findByIdInAndPlatform(ids, platformType)
                .stream()
                .collect(Collectors.toMap(Account::getId, a -> a));

        List<Account> accounts = dtoList.stream()
                .map(dto -> {
                    if (existingAccounts.containsKey(dto.getId())) {
                        return accountMapper.updateAccount(existingAccounts.get(dto.getId()), dto);
                    }
                    return accountMapper.toNewAccount(dto);
                }).collect(Collectors.toList());
        return accountRepository.saveAll(accounts);
    }
}
