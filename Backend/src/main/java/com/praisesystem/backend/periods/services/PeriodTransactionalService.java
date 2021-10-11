package com.praisesystem.backend.periods.services;

import com.praisesystem.backend.periods.dto.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.PeriodDto;

import java.time.LocalDate;

public interface PeriodTransactionalService {
    PeriodDto create(CreatePeriodRequestDto dto);

    LocalDate getLastPeriodDate();
}
