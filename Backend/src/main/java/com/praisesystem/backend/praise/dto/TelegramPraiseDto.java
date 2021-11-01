package com.praisesystem.backend.praise.dto;

import lombok.Value;

@Value
public class TelegramPraiseDto {
    Long id;

    Long periodId;

    String reason;

    String telegramChannelId;

    String giverTelegramId;

    String recipientTelegramId;
}
