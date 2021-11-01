package com.praisesystem.backend.periods.impl;

import com.praisesystem.backend.periods.dto.request.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.response.PeriodDto;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.periods.services.PeriodService;
import com.praisesystem.backend.periods.services.PeriodTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PeriodServiceImpl implements PeriodService {

    PeriodTransactionalService periodTransactionalService;

    @Override
    public List<PeriodDto> findAllPeriods() {
        return periodTransactionalService.findAllPeriods();
    }

    @Override
    public LocalDateTime getLastPeriodEndDate() {
        return periodTransactionalService.getLastPeriodDate();
    }

    @Override
    public PeriodDto create(CreatePeriodRequestDto dto) {
        return periodTransactionalService.create(dto);
    }

    @Override
    public PeriodDto findLastPeriod() {
        return periodTransactionalService.findLastPeriod();
    }

    @Override
    public Period findLastPeriodEntity() {
        return periodTransactionalService.findLastPeriodEntity();
    }
}
