package com.praisesystem.backend.accounts.mapper;

import com.praisesystem.backend.accounts.AccountDto;
import com.praisesystem.backend.accounts.model.Account;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {}
)
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "profileImageURL", source = "profileImageURL")
    Account toNewAccount(AccountDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "platform", ignore = true)
    Account updateAccount(@MappingTarget Account account, AccountDto dto);

    AccountDto toAccountDto(Account account);

    List<AccountDto> toAccountDtos(List<Account> accounts);
}
