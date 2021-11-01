package com.praisesystem.backend.praise.impl;

import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.periods.services.PeriodService;
import com.praisesystem.backend.praise.PraiseRepository;
import com.praisesystem.backend.praise.dto.CreateTelegramPraiseDto;
import com.praisesystem.backend.praise.dto.TelegramPraiseDto;
import com.praisesystem.backend.praise.mapper.PraiseMapper;
import com.praisesystem.backend.praise.model.Praise;
import com.praisesystem.backend.praise.services.PraiseTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PraiseTransactionalServiceImpl implements PraiseTransactionalService {

    PraiseRepository praiseRepository;
    PraiseMapper praiseMapper;
    PeriodService periodService;

    @Override
    public List<TelegramPraiseDto> createTelegramPraise(CreateTelegramPraiseDto dto) {
        Period period = periodService.findLastPeriodEntity();
        List<Praise> praises = dto.getRecipientIds()
                .stream()
                .map(id -> praiseMapper.toNewTelegramPraise(id, dto, period))
                .collect(Collectors.toList());
        return praiseRepository.saveAll(praises).stream().map(praiseMapper::toTelegramPraiseDto).collect(Collectors.toList());
    }
}
