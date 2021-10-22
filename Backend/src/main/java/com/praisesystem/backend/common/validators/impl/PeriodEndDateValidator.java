package com.praisesystem.backend.common.validators.impl;

import com.praisesystem.backend.common.validators.PeriodEndDate;
import com.praisesystem.backend.periods.services.PeriodService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PeriodEndDateValidator implements ConstraintValidator<PeriodEndDate, LocalDateTime> {

   private final PeriodService periodService;
   private int minimumDays;

    public void initialize(PeriodEndDate constraint) {
       minimumDays = constraint.minimumDays();
    }

    public boolean isValid(LocalDateTime endDate, ConstraintValidatorContext context) {
       LocalDateTime lastDate = periodService.getLastPeriodEndDate();
       return lastDate == null || lastDate.plusDays(minimumDays).isBefore(endDate);
    }
}
