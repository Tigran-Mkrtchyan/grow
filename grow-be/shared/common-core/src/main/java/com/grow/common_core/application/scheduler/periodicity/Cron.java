package com.grow.common_core.application.scheduler.periodicity;


import com.grow.kernel.domain.exception.DomainObjectException;

import java.time.ZonedDateTime;

import static com.grow.kernel.domain.validation.Preconditions.requireNonNull;

public class Cron extends Periodicity {

    private String expression;

    public Cron(ZonedDateTime startTime, String expression) {
        super(startTime);
        this.setExpression(expression);
    }

    public Cron(ZonedDateTime startTime, PeriodicityType expression) {
        super(startTime);
        this.setExpression(expression.getCroneExpression());
    }

    public String expression() {
        return this.expression;
    }

    private void setExpression(String expression) {
        requireNonNull(expression, new DomainObjectException("Cron expression must not be null"));
        this.expression = expression;
    }

}
