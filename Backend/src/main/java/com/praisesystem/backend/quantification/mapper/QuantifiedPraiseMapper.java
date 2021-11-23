package com.praisesystem.backend.quantification.mapper;

import com.praisesystem.backend.quantification.mapper.support.QuantifiedPraiseIds;
import com.praisesystem.backend.quantification.model.QuantifiedPraise;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {}
)
public interface QuantifiedPraiseMapper {

    @QuantifiedPraiseIds
    static Set<Long> toQuantifiedPraiseIds(Set<QuantifiedPraise> praises) {
        return praises.stream().map(QuantifiedPraise::getId).collect(Collectors.toSet());
    }
}
