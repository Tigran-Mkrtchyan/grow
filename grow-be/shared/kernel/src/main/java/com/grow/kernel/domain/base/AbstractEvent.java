package com.grow.kernel.domain.base;

import java.time.Instant;

public abstract class AbstractEvent {

    private final Instant occurredOn;

    public AbstractEvent() {
        this.occurredOn = Instant.now();
    }

    public Instant occurredOn() {
        return this.occurredOn;
    }

}
