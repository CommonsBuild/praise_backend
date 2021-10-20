package com.praisesystem.backend.periods.services;

import com.praisesystem.backend.periods.dto.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.PeriodDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PeriodService {

    List<PeriodDto> findAllPeriods();

    LocalDateTime getLastPeriodEndDate();

    PeriodDto create(CreatePeriodRequestDto dto);

    PeriodDto findLastPeriod();

}
