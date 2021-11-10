package com.praisesystem.backend.praise.dto;

import com.praisesystem.backend.accounts.AccountDto;
import lombok.Value;

import java.util.List;

@Value
public class CreatePraiseDto {

    AccountDto giver;

    List<AccountDto> recipients;

    String channelId;

    String channelName;

    String praiseReason;
}
