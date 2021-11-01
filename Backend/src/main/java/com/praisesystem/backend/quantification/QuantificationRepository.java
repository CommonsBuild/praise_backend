package com.praisesystem.backend.quantification;

import com.praisesystem.backend.quantification.model.QuantifiedPraise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuantificationRepository extends JpaRepository<QuantifiedPraise, Long> {
    List<QuantifiedPraise> findAllByQuantifier_IdAndPeriod_Id(Long userId, Long periodId);
}
