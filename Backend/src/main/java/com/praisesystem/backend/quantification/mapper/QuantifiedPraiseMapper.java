package com.praisesystem.backend.quantification.mapper;

import com.praisesystem.backend.quantification.QuantifiedPraiseDto;
import com.praisesystem.backend.quantification.mapper.support.QuantifiedPraiseIds;
import com.praisesystem.backend.quantification.model.QuantifiedPraise;
import com.praisesystem.backend.users.mapper.UserMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class}
)
public interface QuantifiedPraiseMapper {

    @Mapping(target = "periodId", source = "period.id")
    @Mapping(target = "praiseId", source = "praise.id")
    QuantifiedPraiseDto toQuantifiedPraiseDto(QuantifiedPraise praise);

//    Set<QuantifiedPraiseDto> toQuantifiedPraiseDtos(Set<QuantifiedPraise> quantifiedPraises);

    @QuantifiedPraiseIds
    static Set<Long> toQuantifiedPraiseIds(Set<QuantifiedPraise> praises) {
        return praises.stream().map(QuantifiedPraise::getId).collect(Collectors.toSet());
    }
}
