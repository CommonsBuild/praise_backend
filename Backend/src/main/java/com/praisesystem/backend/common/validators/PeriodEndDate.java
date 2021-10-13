package com.praisesystem.backend.common.validators;

import com.praisesystem.backend.common.validators.impl.PeriodEndDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PeriodEndDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PeriodEndDate {
    String message() default "Period end date must be minimum one week later than last date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int minimumDays() default 7;
}
