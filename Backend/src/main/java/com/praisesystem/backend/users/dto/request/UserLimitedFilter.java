package com.praisesystem.backend.users.dto.request;

import lombok.Value;

@Value
public class UserLimitedFilter {
    String discordTag;
    String telegramHandle;
}
