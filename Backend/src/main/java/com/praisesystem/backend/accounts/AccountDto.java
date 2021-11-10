package com.praisesystem.backend.accounts;

import com.praisesystem.backend.accounts.enums.PlatformType;
import lombok.Value;

@Value
public class AccountDto {
    String accountId;
    String username;
    String profileImageURL;
    PlatformType platform;
}
