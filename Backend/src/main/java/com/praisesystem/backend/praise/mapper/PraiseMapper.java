package com.praisesystem.backend.praise.mapper;

import com.praisesystem.backend.accounts.model.Account;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.model.Praise;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {}
)
public interface PraiseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "quantifiedPraises", ignore = true)
    @Mapping(target = "period", source = "period")
    @Mapping(target = "reason", source = "dto.praiseReason")
    @Mapping(target = "giver", source = "giver")
    @Mapping(target = "recipient", source = "recipient")
    Praise toNewPraise(Account giver, Account recipient, CreatePraiseDto dto, Period period);
}
