package com.praisesystem.backend.periods.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(description = "Period object")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PeriodDto {
    @Schema(description = "Period ID")
    Long id;

    @Schema(description = "Period name")
    String name;

    @Schema(description = "Period end date")
    LocalDateTime endDate;

    @Schema(description = "Total number of praises")
    Long totalPraises;
}
