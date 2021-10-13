package com.praisesystem.backend.periods.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CreatePeriodRequestDto {

    String name;

    LocalDateTime endDate;
}
