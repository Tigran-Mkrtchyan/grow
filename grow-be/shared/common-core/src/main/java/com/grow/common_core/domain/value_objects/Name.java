package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static com.grow.kernel.domain.validation.Preconditions.requireNonBlank;

public class Name implements ValueObject, Comparable<Name> {

    private String value;

    protected Name() {
    }

    public Name(String value) {
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    public boolean equalsIgnoreCase(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return value.equalsIgnoreCase(name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    private void setValue(String value) {
        requireNonBlank(value, new IllegalArgumentException("Name value must not be null"));
        this.value = value.trim();
    }

    public static boolean isValid(String value) {
        return StringUtils.isNoneBlank(value);
    }

    @Override
    public int compareTo(@NonNull Name o) {
        return this.value.compareTo(o.value);
    }

}
