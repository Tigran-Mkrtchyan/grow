package com.grow.kernel.domain.base;

import com.grow.kernel.domain.exception.DomainObjectException;
import lombok.experimental.FieldNameConstants;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static com.grow.kernel.domain.validation.Preconditions.requireNonNull;

/**
 * Base class for entities.
 *
 * @param <ID> the entity ID type.
 */
@FieldNameConstants(innerTypeName = "Field")
public abstract class AbstractEntity<ID extends DomainObjectId> implements IdentifiableDomainObject<ID> {

    private Long internalId;
    private LocalDateTime createdDate;
    private ID id;

    protected List<DomainEvent> domainEvents = new ArrayList<>();

    public List<? extends DomainEvent> fireEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    protected <T extends DomainEvent> void registerEvent(T event) {
        requireNonNull(event, new DomainObjectException("Event must not be null"));
        this.domainEvents.add(event);
    }

    /**
     * Default constructor
     */
    protected AbstractEntity() {

    }

    /**
     * Copy constructor
     *
     * @param source the entity to copy from.
     */
    protected AbstractEntity(AbstractEntity<ID> source) {
        this.id = source.id;
        this.createdDate = source.createdDate;
    }

    /**
     * Constructor for creating new entities.
     *
     * @param id the ID to assign to the entity.
     */
    protected AbstractEntity(ID id) {
        this.setId(id);
        this.createdDate = LocalDateTime.now(ZoneOffset.UTC);
    }

    @Override
    @NonNull
    public ID id() {
        return id;
    }

    @Override
    @NonNull
    public Long internalId() {
        return internalId;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass") // We do this with a Spring function
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }

        var other = (AbstractEntity<?>) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }
    public LocalDateTime createdDate() {
        return createdDate;
    }
    private void setId(ID id) {
        requireNonNull(id, new DomainObjectException("Abstract entity id must not be null"));
        this.id = id;
    }

}
