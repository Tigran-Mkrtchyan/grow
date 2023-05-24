package com.grow.kernel.domain.base;

import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.lang.NonNull;

import java.util.Collection;

/**
 * Base class for aggregate roots.
 *
 * @param <ID> the aggregate root ID type.
 */
public abstract class AbstractAggregateRoot<ID extends DomainObjectId> extends AbstractEntity<ID> {

    @DomainEvents
    public Collection<? extends DomainEvent> events() {
        return super.domainEvents;
    }

    @AfterDomainEventPublication
    public void clearEvents() {
        domainEvents.clear();
    }

    /**
     * Default constructor
     */
    protected AbstractAggregateRoot() {
    }

    /**
     * Copy constructor. Please note that any registered domain events are <b>not</b> copied.
     *
     * @param source the aggregate root to copy from.
     */
    protected AbstractAggregateRoot(@NonNull AbstractAggregateRoot<ID> source) {
        super(source);
    }

    /**
     * Constructor for creating new aggregate roots.
     *
     * @param id the ID to assign to the aggregate root.
     */
    protected AbstractAggregateRoot(ID id) {
        super(id);
    }
}
