package com.praisesystem.backend.quantification.impl;

import com.praisesystem.backend.quantification.QuantificationRepository;
import com.praisesystem.backend.quantification.model.QuantifiedPraise;
import com.praisesystem.backend.quantification.services.QuantificationTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuantificationTransactionalServiceImpl implements QuantificationTransactionalService {

    QuantificationRepository repository;

    @Override
    public List<QuantifiedPraise> getPeriodQuantificationInfo(Long userId, Long periodId) {
        return repository.findAllByQuantifier_IdAndPeriod_Id(userId, periodId);
    }
}
