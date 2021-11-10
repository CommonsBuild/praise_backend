package com.praisesystem.backend.accounts;

import com.praisesystem.backend.accounts.enums.PlatformType;
import com.praisesystem.backend.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
    Account findByAccountIdAndPlatform(String accountId, PlatformType type);
    List<Account> findByAccountIdInAndPlatform(Collection<String> ids, PlatformType type);
}
