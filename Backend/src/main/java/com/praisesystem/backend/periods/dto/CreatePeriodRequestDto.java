package com.praisesystem.backend.periods.dto;

import com.praisesystem.backend.common.validators.PeriodEndDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Value
@Schema(description = "Period creation object")
public class CreatePeriodRequestDto {

    @NotBlank
    @Schema(description = "Period name")
    String name;

    @PeriodEndDate(minimumDays = 7)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Schema(description = "Period end date")
    LocalDateTime endDate;
}
