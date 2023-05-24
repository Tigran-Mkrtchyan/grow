package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class BirthDate implements ValueObject {

    private LocalDate value;

    protected BirthDate() {
    }

    public BirthDate(LocalDate value) {
        this.setBirthDate(value);
    }

    public LocalDate getValue() {
        return value;
    }

    public long ageInMonth() {
        return ChronoUnit.MONTHS.between(value, LocalDate.now());
    }

    private void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalStateException("Birthdate must not be null");
        }
        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalStateException("Birthdate must be before now");
        }
        this.value = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthDate birthDate = (BirthDate) o;
        return Objects.equals(this.value, birthDate.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
