package com.praisesystem.backend.periods.impl;

import com.praisesystem.backend.common.exceptions.Precondition;
import com.praisesystem.backend.common.exceptions.exceptiontypes.NotFoundObjectException;
import com.praisesystem.backend.common.exceptions.exceptiontypes.ValidationException;
import com.praisesystem.backend.periods.PeriodMapper;
import com.praisesystem.backend.periods.PeriodRepository;
import com.praisesystem.backend.periods.dto.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.PeriodDto;
import com.praisesystem.backend.periods.model.PeriodEntity;
import com.praisesystem.backend.periods.services.PeriodTransactionalService;
import com.praisesystem.backend.users.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PeriodTransactionalServiceImpl implements PeriodTransactionalService {

    PeriodRepository periodRepository;
    PeriodMapper periodMapper;
    UserService userService;

    @Override
    public LocalDateTime getLastPeriodDate() {
        PeriodEntity periodEntity = periodRepository.findFirstByOrderByIdDesc();
        return periodEntity == null ? null : periodEntity.getEndDate();
    }

    @Override
    public PeriodDto findLastPeriod() {
        PeriodEntity periodEntity = periodRepository.findFirstByOrderByIdDesc();
        if (periodEntity == null) {
            throw new NotFoundObjectException("No period found");
        }
        return periodMapper.toPeriodDto(periodEntity);
    }

    @Override
    public List<PeriodDto> findAllPeriods() {
        return periodRepository.findAll().stream().map(periodMapper::toPeriodDto).collect(Collectors.toList());
    }

    @Override
    public PeriodDto create(CreatePeriodRequestDto dto) {
        Precondition.ifTrueThrow(periodRepository.existsByName(dto.getName()), new ValidationException("Period with this name already exists"));

        PeriodEntity newPeriod = periodMapper.toNewPeriod(dto);
        newPeriod = periodRepository.save(newPeriod);

        return periodMapper.toPeriodDto(newPeriod);
    }
}
