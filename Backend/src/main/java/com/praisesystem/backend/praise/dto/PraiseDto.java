package com.praisesystem.backend.praise.dto;

import com.praisesystem.backend.accounts.dto.AccountDto;
import com.praisesystem.backend.source.dto.SourceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.Set;

@Value
public class PraiseDto {
    @Schema(description = "Praise ID")
    Long id;

    @Schema(description = "Period ID")
    Long periodId;

    @Schema(description = "Quantified praise IDs")
    Set<Long> quantifiedPraises;

    @Schema(description = "Praise reason")
    String reason;

    @Schema(description = "Giver account")
    AccountDto giver;

    @Schema(description = "Receiver account")
    AccountDto recipient;

    @Schema(description = "Source info")
    SourceDto source;

}
