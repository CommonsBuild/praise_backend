package com.praisesystem.backend.periods.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Schema(description = "Period object")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PeriodDto {
    @Schema(description = "Period ID")
    Long id;

    @Schema(description = "Period name")
    String name;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Schema(description = "Period end date", pattern = "dd.MM.yyyy")
    LocalDate endDate;

//    @Schema(description = "Quantifiers list")
//    List<UserDto> quantifiers;
}
