package com.praisesystem.backend.periods;

import com.praisesystem.backend.periods.model.PeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PeriodRepository extends JpaRepository<PeriodEntity, Long> {
    PeriodEntity findFirstByOrderByIdDesc();

    boolean existsByName(String name);

    boolean existsByEndDateAfter(LocalDate localDate);
}
