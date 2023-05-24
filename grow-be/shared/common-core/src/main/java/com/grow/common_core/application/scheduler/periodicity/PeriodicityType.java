package com.grow.common_core.application.scheduler.periodicity;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum PeriodicityType {

    // Every three minute
    EVERY_THREE_MINUTE("0 0/3 * 1/1 * ? *"),
    // At 00:00:00am every day
    DAILY("0 0 0 * * ? *"),
    // At 00:00:00am, on every Sunday, every month
    WEEKLY("0 0 0 ? * 1 *"),
    // At 00:00:00am, on the 1st day, every month
    MONTHLY("0 0 0 1 * ? *"),
    // At 00:00:00am, on the 1st day, in January
    YEARLY("0 0 0 1 1 ? *"),
    EVERY_MONDAY_AT_10("0 0 10 ? * MON"),
    DAILY_AT_10("0 0 10 ? * *");

    private final String croneExpression;

    PeriodicityType(String croneExpression) {
        this.croneExpression = croneExpression;
    }

    public String getCroneExpression() {
        return croneExpression;
    }

    public static Optional<PeriodicityType> fromString(String str) {
        if (StringUtils.isEmpty(str)) {
            return Optional.empty();
        }
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(str))
                .findAny();
    }

}