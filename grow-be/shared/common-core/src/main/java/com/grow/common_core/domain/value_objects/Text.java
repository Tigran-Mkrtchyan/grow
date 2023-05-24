package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static com.grow.kernel.domain.validation.Preconditions.requireNonBlank;

public class Text implements ValueObject {

    private String value;

    public Text(String value) {
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public static boolean isValid(String value) {
        return StringUtils.isNoneBlank(value);
    }

    private void setValue(String value) {
        requireNonBlank(value, new IllegalArgumentException("Text value must not be null"));
        this.value = value.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(value, text.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
