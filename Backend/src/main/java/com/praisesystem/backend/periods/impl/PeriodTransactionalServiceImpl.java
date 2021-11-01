package com.praisesystem.backend.periods.impl;

import com.praisesystem.backend.common.exceptions.Precondition;
import com.praisesystem.backend.common.exceptions.exceptiontypes.NotFoundObjectException;
import com.praisesystem.backend.common.exceptions.exceptiontypes.ValidationException;
import com.praisesystem.backend.periods.PeriodMapper;
import com.praisesystem.backend.periods.dto.request.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.response.PeriodDto;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.periods.repositories.PeriodRepository;
import com.praisesystem.backend.periods.repositories.PeriodUserRepository;
import com.praisesystem.backend.periods.services.PeriodTransactionalService;
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
    PeriodUserRepository userRepository;
    PeriodMapper periodMapper;

    @Override
    public LocalDateTime getLastPeriodDate() {
        Period period = periodRepository.findFirstByOrderByIdDesc();
        return period == null ? null : period.getEndDate();
    }

    @Override
    public PeriodDto findLastPeriod() {
        Period period = periodRepository.findFirstByOrderByIdDesc();
        if (period == null) {
            throw new NotFoundObjectException("No period found");
        }
        return periodMapper.toPeriodDto(period);
    }

    @Override
    public Period findLastPeriodEntity() {
        return periodRepository.findFirstByOrderByIdDesc();
    }

    @Override
    public PeriodDto renamePeriod(Long id, String name) {
        Precondition.ifTrueThrow(id == null, new ValidationException("Id must be present"));
        Precondition.ifTrueThrow(name == null || name.isBlank(), new ValidationException("Name must be present"));

        Period period = periodRepository.findById(id).orElseThrow(() -> new NotFoundObjectException("Period not found"));

        period.setName(name);
        period = periodRepository.save(period);

        return periodMapper.toPeriodDto(period);
    }

    @Override
    public List<PeriodDto> findAllPeriods() {
        return periodRepository.findAll().stream().map(periodMapper::toPeriodDto).collect(Collectors.toList());
    }

    @Override
    public PeriodDto create(CreatePeriodRequestDto dto) {
        Precondition.ifTrueThrow(periodRepository.existsByName(dto.getName()), new ValidationException("Period with this name already exists"));

        Period newPeriod = periodMapper.toNewPeriod(dto);
        newPeriod = periodRepository.save(newPeriod);

        return periodMapper.toPeriodDto(newPeriod);
    }
}
