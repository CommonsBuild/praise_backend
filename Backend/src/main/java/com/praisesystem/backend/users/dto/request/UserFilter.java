package com.praisesystem.backend.users.dto.request;

import lombok.Value;

@Value
public class UserFilter {
    String ethereumAddress;
    String discordTag;
    String telegramHandle;
}
