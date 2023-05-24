package com.grow.kernel.domain.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.function.BooleanSupplier;

public class Preconditions {
    private Preconditions() {
    }

    public static void requireNonNull(Object o, RuntimeException exception) {
        if (o == null) {
            throw exception;
        }
    }

    public static void requireNonNull(Object o, BooleanSupplier condition, RuntimeException exception) {
        if (Boolean.TRUE.equals(condition.getAsBoolean()) &&
                o == null) {
            throw exception;
        }
    }

    public static void requireNull(Object o, RuntimeException exception) {
        if (o != null) {
            throw exception;
        }
    }

    public static void requireNonBlank(String s, RuntimeException exception) {
        if (StringUtils.isBlank(s)) {
            throw exception;
        }
    }

    public static void requireNonBlank(String s, BooleanSupplier condition, RuntimeException exception) {
        if (Boolean.TRUE.equals(condition.getAsBoolean()) &&
                StringUtils.isBlank(s)) {
            throw exception;
        }
    }

    public static void requireFalse(BooleanSupplier condition, RuntimeException exception) {
        if (Boolean.TRUE.equals(condition.getAsBoolean())) {
            throw exception;
        }
    }

    public static void requireTrue(BooleanSupplier condition, RuntimeException exception) {
        if (Boolean.FALSE.equals(condition.getAsBoolean())) {
            throw exception;
        }
    }
}
