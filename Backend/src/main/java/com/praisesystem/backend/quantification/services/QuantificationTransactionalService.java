package com.praisesystem.backend.quantification.services;

import com.praisesystem.backend.quantification.model.QuantifiedPraise;

import java.util.List;

public interface QuantificationTransactionalService {
    List<QuantifiedPraise> getPeriodQuantificationInfo(Long userId, Long periodId);
}
