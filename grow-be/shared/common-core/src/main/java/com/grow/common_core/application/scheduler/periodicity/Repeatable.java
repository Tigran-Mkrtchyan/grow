package com.grow.common_core.application.scheduler.periodicity;

import com.grow.kernel.domain.exception.DomainObjectException;
import com.grow.kernel.domain.validation.Preconditions;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

import static com.grow.kernel.domain.validation.Preconditions.requireNonNull;

public class Repeatable extends Periodicity {

    private int repeatIntervalInSeconds;
    private Integer repeatCount;
    private ZonedDateTime endTime;

    public Repeatable(ZonedDateTime startTime, int repeatIntervalInSeconds) {
        super(startTime);
        this.setRepeatIntervalInSeconds(repeatIntervalInSeconds);
    }

    public Repeatable(ZonedDateTime startTime, int repeatIntervalInSeconds, int repeatCount) {
        super(startTime);
        this.setRepeatIntervalInSeconds(repeatIntervalInSeconds);
        this.setRepeatCount(repeatCount);
    }

    public Repeatable(ZonedDateTime startTime, ZonedDateTime endTime, int repeatIntervalInSeconds) {
        super(startTime);
        this.setEndTime(endTime);
        this.setRepeatIntervalInSeconds(repeatIntervalInSeconds);
    }

    public Repeatable(ZonedDateTime startTime, ZonedDateTime endTime,
                      int repeatIntervalInSeconds, int repeatCount) {
        super(startTime);
        this.setEndTime(endTime);
        this.setRepeatIntervalInSeconds(repeatIntervalInSeconds);
        this.setRepeatCount(repeatCount);
    }

    public boolean repeatForever() {
        return this.repeatCount == null;
    }

    public boolean indefinite() {
        return this.endTime == null;
    }

    @Nullable
    public Integer repeatCount() {
        return this.repeatCount;
    }

    public int repeatIntervalInSeconds() {
        return this.repeatIntervalInSeconds;
    }

    @Nullable
    public ZonedDateTime endTime() {
        return this.endTime;
    }

    private void setEndTime(ZonedDateTime endTime) {
        requireNonNull(endTime, new DomainObjectException("End time must not be null"));
        Preconditions.requireTrue(() -> endTime.isAfter(this.startTime()), new DomainObjectException(
                "End time must be after star time"));
        this.endTime = endTime;
    }

    private void setRepeatIntervalInSeconds(int repeatInterval) {
        Preconditions.requireTrue(() -> repeatInterval >= 0, new DomainObjectException(
                "Repeat interval in seconds must be greater or equal 0"));
        this.repeatIntervalInSeconds = repeatInterval;
    }

    private void setRepeatCount(int repeatCount) {
        Preconditions.requireFalse(() -> repeatCount < 0, new DomainObjectException(
                "Repeat count must be greater 0."));
        Preconditions.requireTrue(() -> repeatCount != 0, new DomainObjectException(
                "Repeat count must not be 0.Use OneTime periodicity"));
        this.repeatCount = repeatCount;
    }

}
