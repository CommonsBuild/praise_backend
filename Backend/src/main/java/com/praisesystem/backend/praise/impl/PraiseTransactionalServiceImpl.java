package com.praisesystem.backend.praise.impl;

import com.praisesystem.backend.accounts.AccountService;
import com.praisesystem.backend.accounts.model.Account;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.periods.services.PeriodService;
import com.praisesystem.backend.praise.PraiseRepository;
import com.praisesystem.backend.praise.dto.CreatePraiseDto;
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
    AccountService accountService;

    @Override
    public List<Praise> createPraise(CreatePraiseDto dto) {
        // find last period
        Period period = periodService.findLastPeriodEntity();

        Account giver = accountService.createOrUpdateAccount(dto.getGiver());
        List<Account> recipients = accountService.createOrUpdateAccounts(dto.getRecipients());

        // Create praise object for each recipient
        List<Praise> praises = recipients
                .stream()
                .map(recipient -> praiseMapper.toNewPraise(giver, recipient, dto, period))
                .collect(Collectors.toList());

        return praiseRepository.saveAll(praises);
    }
}
