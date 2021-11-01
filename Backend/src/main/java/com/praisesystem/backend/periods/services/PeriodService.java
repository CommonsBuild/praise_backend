package com.praisesystem.backend.periods.services;

import com.praisesystem.backend.periods.dto.request.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.response.PeriodDto;
import com.praisesystem.backend.periods.model.Period;

import java.time.LocalDateTime;
import java.util.List;

public interface PeriodService {

    List<PeriodDto> findAllPeriods();

    LocalDateTime getLastPeriodEndDate();

    PeriodDto create(CreatePeriodRequestDto dto);

    PeriodDto findLastPeriod();

    Period findLastPeriodEntity();

    PeriodDto renamePeriod(Long id, String name);
}
