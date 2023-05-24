package com.grow.common_core.domain.value_objects;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PhotoGallery implements Serializable {

    private Set<Photo> value;

    public PhotoGallery() {
        this.value = new HashSet<>();
    }

    public PhotoGallery(Set<Photo> value) {
        this.value = Objects.requireNonNullElseGet(value, HashSet::new);
    }

    public void add(Photo value) {
        this.value.add(value);
    }

    public void remove(Photo value) {
        if (value == null) {
            return;
        }
        this.value.remove(value);
    }

    public Set<Photo> getValue() {
        return value;
    }

    public boolean contains(Photo value) {
        return this.value.contains(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoGallery that = (PhotoGallery) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
