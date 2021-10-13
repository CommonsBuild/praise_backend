package com.praisesystem.backend.periods.services;

import com.praisesystem.backend.periods.dto.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.PeriodDto;

import java.time.LocalDate;

public interface PeriodService {
    LocalDate getLastPeriodEndDate();

    PeriodDto create(CreatePeriodRequestDto dto);
}
