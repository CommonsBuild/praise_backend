package com.praisesystem.backend.praise.impl;

import com.praisesystem.backend.accounts.AccountService;
import com.praisesystem.backend.accounts.model.Account;
import com.praisesystem.backend.common.exceptions.exceptiontypes.NotFoundObjectException;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.periods.services.PeriodService;
import com.praisesystem.backend.praise.PraiseRepository;
import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.mapper.PraiseMapper;
import com.praisesystem.backend.praise.model.Praise;
import com.praisesystem.backend.praise.services.PraiseTransactionalService;
import com.praisesystem.backend.source.SourceService;
import com.praisesystem.backend.source.model.Source;
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
    AccountService accountService;
    SourceService sourceService;

    @Override
    public Praise findById(Long id) {
        Praise praise = praiseRepository.findById(id).orElseThrow(() -> new NotFoundObjectException("Praise not found"));
        return praise;
    }

    @Override
    public List<Praise> createPraise(CreatePraiseDto dto) {
        // find last period
        Period period = periodService.findLastPeriodEntity();

        Account giver = accountService.createOrUpdateAccount(dto.getGiver());
        List<Account> recipients = accountService.createOrUpdateAccounts(dto.getRecipients());
        Source source = sourceService.createOrUpdate(dto.getSource());
        // Create praise object for each recipient
        List<Praise> praises = recipients
                .stream()
                .map(recipient -> praiseMapper.toNewPraise(giver, recipient, source, period, dto.getPraiseReason()))
                .collect(Collectors.toList());

        return praiseRepository.saveAll(praises);
    }
}
