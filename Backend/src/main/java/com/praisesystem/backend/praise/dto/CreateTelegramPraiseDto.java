package com.praisesystem.backend.praise.dto;

import lombok.Value;

import java.util.List;

@Value
public class CreateTelegramPraiseDto {

    String giverId;

    List<String> recipientIds;

    String channelId;

    String praiseReason;
}
