package com.grow.common_core.application.scheduler.periodicity;

import com.grow.kernel.domain.base.ValueObject;

import java.time.ZonedDateTime;

import static com.grow.kernel.domain.validation.Preconditions.requireNonNull;

public abstract class Periodicity implements ValueObject {

    private ZonedDateTime startTime;

    public Periodicity(ZonedDateTime startTime) {
        requireNonNull(startTime, new IllegalArgumentException("Periodicity start time must not be null"));
        this.startTime = startTime;
    }

    public ZonedDateTime startTime() {
        return this.startTime;
    }

}
