package com.praisesystem.backend.praise.repository;

import com.praisesystem.backend.periods.enums.PeriodStatus;
import com.praisesystem.backend.periods.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PraisePeriodRepository extends JpaRepository<Period, Long> {
    Period findFirstByStatusAndEndDateAfterOrderByEndDateAsc(PeriodStatus status, LocalDateTime now);
}
