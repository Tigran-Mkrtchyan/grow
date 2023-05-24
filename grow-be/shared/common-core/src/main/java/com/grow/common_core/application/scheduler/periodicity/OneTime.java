package com.grow.common_core.application.scheduler.periodicity;

import java.time.ZonedDateTime;

public class OneTime extends Periodicity {

    public OneTime(ZonedDateTime startTime) {
        super(startTime);
    }

}
