package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;

import java.util.Objects;

public class PhoneNumber implements ValueObject {

    private static final String PHONE_REGEX = "^\\+([0-9]){7,}$"; //start with '+' and have minimum 7 digits (only digits)

    private String value;

    public PhoneNumber(String number) {
        this.setValue(number);
    }

    protected PhoneNumber() {

    }

    public static boolean isValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches(PHONE_REGEX);
    }

    private void setValue(String number) {
        if (!PhoneNumber.isValid(number)) {
            throw new IllegalStateException("Unsupported type for phone number");
        }
        this.value = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
