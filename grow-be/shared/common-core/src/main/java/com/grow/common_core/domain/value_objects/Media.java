package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;
import com.grow.kernel.domain.exception.DomainObjectException;

import java.net.URL;
import java.util.Objects;

import static com.grow.kernel.domain.validation.Preconditions.requireNonNull;

public class Media implements ValueObject {
    private MediaType type;
    private URL url;
    private Name name;

    public Media(MediaType type, URL url, Name name) {
        setType(type);
        setUrl(url);
        setName(name);
    }

    protected Media() {
    }

    public MediaType getType() {
        return type;
    }

    public URL getUrl() {
        return url;
    }

    public Name getName() {
        return name;
    }

    private void setType(MediaType type) {
        requireNonNull(type, new DomainObjectException("Media type must not be null"));
        this.type = type;
    }

    private void setName(Name name) {
        requireNonNull(name, new DomainObjectException("Media name must not be null"));
        this.name = name;
    }

    private void setUrl(URL url) {
        requireNonNull(url, new DomainObjectException("Media url must not be null"));
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return type == media.type && Objects.equals(url, media.url) && Objects.equals(name, media.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, url, name);
    }
}
