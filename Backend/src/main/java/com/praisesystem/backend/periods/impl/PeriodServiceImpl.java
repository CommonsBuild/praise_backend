package com.praisesystem.backend.periods.impl;

import com.praisesystem.backend.periods.dto.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.PeriodDto;
import com.praisesystem.backend.periods.services.PeriodService;
import com.praisesystem.backend.periods.services.PeriodTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PeriodServiceImpl implements PeriodService {

    PeriodTransactionalService periodTransactionalService;

    @Override
    public LocalDate getLastPeriodEndDate() {
        return periodTransactionalService.getLastPeriodDate();
    }

    @Override
    public PeriodDto create(CreatePeriodRequestDto dto) {
        return periodTransactionalService.create(dto);
    }
}
