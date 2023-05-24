package com.grow.kernel.domain.base;

import com.grow.kernel.domain.exception.DomainObjectException;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.UUID;

import static com.grow.kernel.domain.validation.Preconditions.requireNonBlank;
import static com.grow.kernel.domain.validation.Preconditions.requireNonNull;


/**
 * Base class for value objects that are used as identifiers for {@link IdentifiableDomainObject}s. These are
 * essentially UUID-wrappers.
 */
public abstract class DomainObjectId implements ValueObject {

    private String id;

    protected DomainObjectId() {
    }

    protected DomainObjectId(DomainObjectId id) {
        this.setUuid(id);
    }

    protected DomainObjectId(String uuid) {
        this.setUuid(uuid);
    }

    /**
     * Creates a new, random instance of the given {@code idClass}.
     */
    @NonNull
    public static <ID extends DomainObjectId> ID randomId(Class<ID> idClass) {
        requireNonNull(idClass, new DomainObjectException("Id class must not be null"));
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException("Could not create new instance of " + idClass, ex);
        }
    }

    public String getId() {
        return id;
    }

    /**
     * Returns the ID as a UUID string.
     */
    @NonNull
    public String toUUID() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainObjectId that = (DomainObjectId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }

    private void setUuid(DomainObjectId id) {
        requireNonNull(id, new DomainObjectException("Domain object id must not be null"));
        this.id = id.toUUID();
    }

    private void setUuid(String value) {
        requireNonBlank(value, new DomainObjectException("Domain object uuid must not be null/empty"));
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(
                    String.format("Unsupported string '%s' format provided for domain object id", value));
        }
        this.id = value;
    }


}
