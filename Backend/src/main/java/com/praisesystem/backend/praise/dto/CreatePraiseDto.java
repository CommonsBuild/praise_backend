package com.praisesystem.backend.praise.dto;

import com.praisesystem.backend.accounts.dto.AccountDto;
import com.praisesystem.backend.source.dto.SourceDto;
import lombok.Value;

import java.util.List;

@Value
public class CreatePraiseDto {

    AccountDto giver;

    List<AccountDto> recipients;

    String praiseReason;

    SourceDto source;
}
