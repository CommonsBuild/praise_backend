package com.praisesystem.backend.periods.repositories;

import com.praisesystem.backend.periods.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {
    Period findFirstByOrderByIdDesc();

    boolean existsByName(String name);

    boolean existsByEndDateAfter(LocalDate localDate);
}
