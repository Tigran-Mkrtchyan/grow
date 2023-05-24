package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;
import lombok.Getter;

import java.net.URL;
import java.util.Objects;

@Getter
public class Photo implements ValueObject {

    URL value;

    public Photo(URL value) {
        this.setValue(value);
    }

    private void setValue(URL value) {
        if (value == null) {
            throw new IllegalStateException("Photo url must not be null");
        }
        this.value = value;
    }

    public URL getValue() {
        return value;
    }

    protected Photo() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo1 = (Photo) o;
        return Objects.equals(value, photo1.value);
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
