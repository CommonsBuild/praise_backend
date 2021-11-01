package com.praisesystem.backend.praise.mapper;

import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.praise.dto.CreateTelegramPraiseDto;
import com.praisesystem.backend.praise.dto.TelegramPraiseDto;
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
    @Mapping(target = "discordChannelName", ignore = true)
    @Mapping(target = "giverDiscordTag", ignore = true)
    @Mapping(target = "recipientDiscordTag", ignore = true)
    @Mapping(source = "period", target = "period")
    @Mapping(source = "dto.giverId", target = "giverTelegramId")
    @Mapping(source = "recipientId", target = "recipientTelegramId")
    @Mapping(source = "dto.channelId", target = "telegramChannelId")
    @Mapping(source = "dto.praiseReason", target = "reason")
    Praise toNewTelegramPraise(String recipientId, CreateTelegramPraiseDto dto, Period period);

    @Mapping(source = "period.id", target = "periodId")
    TelegramPraiseDto toTelegramPraiseDto(Praise praise);
}
