package com.praisesystem.backend.configuration.configs.openapi;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableDto {
    @Parameter(description = "Page number (start from 0)", example = "0")
    int page;

    @Parameter(description = "Page size", example = "20")
    int size;

    @Parameter(description = "Sort criteria. Usage: property[,asc|desc]", example = "id,asc")
    String sort;
}
