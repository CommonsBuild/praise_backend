package com.praisesystem.backend.praise.mapper;

import com.praisesystem.backend.accounts.mapper.AccountMapper;
import com.praisesystem.backend.accounts.model.Account;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.praise.dto.PraiseDto;
import com.praisesystem.backend.praise.model.Praise;
import com.praisesystem.backend.source.mapper.SourceMapper;
import com.praisesystem.backend.source.model.Source;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {AccountMapper.class, SourceMapper.class}
)
public interface PraiseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "quantifiedPraises", ignore = true)
    @Mapping(target = "period", source = "period")
    @Mapping(target = "reason", source = "reason")
    @Mapping(target = "giver", source = "giver")
    @Mapping(target = "recipient", source = "recipient")
    @Mapping(target = "source", source = "source")
    Praise toNewPraise(Account giver, Account recipient, Source source, Period period, String reason);

    PraiseDto toPraiseDto(Praise praise);
}
