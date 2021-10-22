package com.praisesystem.backend.periods.services;

import com.praisesystem.backend.periods.dto.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.PeriodDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PeriodTransactionalService {

    List<PeriodDto> findAllPeriods();

    PeriodDto create(CreatePeriodRequestDto dto);

    LocalDateTime getLastPeriodDate();

    PeriodDto findLastPeriod();

}
