package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;
import com.grow.kernel.domain.exception.DomainObjectException;


import java.net.URL;
import java.util.Objects;

import static com.grow.kernel.domain.validation.Preconditions.requireNonNull;

public class File implements ValueObject {

    private URL url;
    private Name name;

    public File(URL url, Name name) {
        setUrl(url);
        this.setName(name);
    }

    protected File() {
    }

    public Name getName() {
        return this.name;
    }

    private void setName(Name name) {
        requireNonNull(name, new DomainObjectException("File name must not be null"));
        this.name = name;
    }

    private void setUrl(URL url) {
        requireNonNull(url, new DomainObjectException("File url must not be null"));
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(url, file.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
