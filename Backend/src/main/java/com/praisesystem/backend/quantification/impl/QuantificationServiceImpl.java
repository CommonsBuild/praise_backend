package com.praisesystem.backend.quantification.impl;

import com.praisesystem.backend.quantification.model.QuantifiedPraise;
import com.praisesystem.backend.quantification.services.QuantificationService;
import com.praisesystem.backend.quantification.services.QuantificationTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j(topic = "[QUANTIFICATION SERVICE]")
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuantificationServiceImpl implements QuantificationService {

    QuantificationTransactionalService quantificationTransactionalService;

    @Override
    public List<QuantifiedPraise> getPeriodQuantificationInfo(Long userId, Long periodId) {
        return quantificationTransactionalService.getPeriodQuantificationInfo(userId, periodId);
    }
}
