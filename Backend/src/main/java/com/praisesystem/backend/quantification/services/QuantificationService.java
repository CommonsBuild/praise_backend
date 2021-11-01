package com.praisesystem.backend.quantification.services;

import com.praisesystem.backend.quantification.model.QuantifiedPraise;

import java.util.List;

public interface QuantificationService {
    List<QuantifiedPraise> getPeriodQuantificationInfo(Long userId, Long periodId);
}
