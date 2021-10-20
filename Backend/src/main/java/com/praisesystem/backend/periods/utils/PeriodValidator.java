package com.praisesystem.backend.periods.utils;

import java.time.LocalDate;

public class PeriodValidator {

    public static boolean isValidEndDate(LocalDate newEndDate, LocalDate lastEndDate) {
        return newEndDate.isAfter(lastEndDate);
    }
}
