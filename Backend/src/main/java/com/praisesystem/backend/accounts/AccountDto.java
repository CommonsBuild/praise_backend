package com.praisesystem.backend.accounts;

import com.praisesystem.backend.accounts.enums.PlatformType;
import lombok.Value;

@Value
public class AccountDto {
    String id;
    String username;
    String profileImageURL;
    PlatformType platform;
}
