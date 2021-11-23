package com.praisesystem.backend.praise.impl;

import com.praisesystem.backend.accounts.model.Account;
import com.praisesystem.backend.accounts.services.AccountService;
import com.praisesystem.backend.common.exceptions.exceptiontypes.NotFoundObjectException;
import com.praisesystem.backend.periods.enums.PeriodStatus;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.dto.PraiseDto;
import com.praisesystem.backend.praise.mapper.PraiseMapper;
import com.praisesystem.backend.praise.model.Praise;
import com.praisesystem.backend.praise.repository.PraisePeriodRepository;
import com.praisesystem.backend.praise.repository.PraiseRepository;
import com.praisesystem.backend.praise.services.PraiseTransactionalService;
import com.praisesystem.backend.source.model.Source;
import com.praisesystem.backend.source.services.SourceService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PraiseTransactionalServiceImpl implements PraiseTransactionalService {

    PraiseRepository praiseRepository;
    PraisePeriodRepository periodRepository;
    PraiseMapper praiseMapper;
    AccountService accountService;
    SourceService sourceService;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Praise findById(Long id) {
        Praise praise = praiseRepository.findById(id).orElseThrow(() -> new NotFoundObjectException("Praise not found"));
        return praise;
    }

    @Override
    public List<PraiseDto> createPraise(CreatePraiseDto dto) {
        // find last period or null if there is no open periods
        Period period = this.findPeriodForNewPraise();

        Account giver = accountService.createOrUpdateAccount(dto.getGiver());
        List<Account> recipients = accountService.createOrUpdateAccounts(dto.getRecipients());
        Source source = sourceService.createOrUpdate(dto.getSource());

        // Create praise object for each recipient
        List<Praise> praises = recipients
                .stream()
                .map(recipient -> praiseMapper.toNewPraise(giver, recipient, source, period, dto.getPraiseReason()))
                .collect(Collectors.toList());

        return praiseRepository.saveAll(praises).stream().map(praiseMapper::toPraiseDto).collect(Collectors.toList());
    }

    private Period findPeriodForNewPraise() {
        return periodRepository.findFirstByStatusAndEndDateAfterOrderByEndDateAsc(PeriodStatus.OPEN, LocalDateTime.now());
    }
}
