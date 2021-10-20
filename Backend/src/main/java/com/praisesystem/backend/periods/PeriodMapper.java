package com.praisesystem.backend.periods;

import com.praisesystem.backend.common.mappers.CommonMapper;
import com.praisesystem.backend.common.mappers.support.CollectionSize;
import com.praisesystem.backend.periods.dto.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.PeriodDto;
import com.praisesystem.backend.periods.model.PeriodEntity;
import com.praisesystem.backend.users.mapper.UserMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class, CommonMapper.class}
)
public interface PeriodMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "quantifiers", ignore = true)
    @Mapping(target = "praises", ignore = true)
    @Mapping(target = "quantifiedPraises", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "endDate", source = "dto.endDate")
    PeriodEntity toNewPeriod(CreatePeriodRequestDto dto);

    @Mapping(target = "totalPraises", source = "praises", qualifiedBy = CollectionSize.class)
    PeriodDto toPeriodDto(PeriodEntity newPeriod);
}
