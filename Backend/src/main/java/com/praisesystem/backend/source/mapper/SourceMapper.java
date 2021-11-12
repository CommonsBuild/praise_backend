package com.praisesystem.backend.source.mapper;

import com.praisesystem.backend.source.SourceDto;
import com.praisesystem.backend.source.model.Source;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {}
)
public interface SourceMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Source toNewSource(SourceDto source);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Source updateSource(@MappingTarget Source source, SourceDto dto);

    SourceDto toSourceDto(Source source);
}
