package com.praisesystem.backend.praise.dto;

import com.praisesystem.backend.accounts.AccountDto;
import com.praisesystem.backend.source.SourceDto;
import lombok.Value;

import java.util.List;

@Value
public class CreatePraiseDto {

    AccountDto giver;

    List<AccountDto> recipients;

    String praiseReason;

    SourceDto source;
}
