package com.grow.common_core.application.scheduler.key;

import com.grow.kernel.domain.base.ValueObject;

/**
 * Used to identify scheduled jobs.
 *
 * @apiNote For every use case should be new implementation best describing it which
 * is globally unique
 * @apiNote Changing keys can cause unpredictable result as there can already be
 * scheduled jobs with old one and new key will no longer refer to them
 */
public interface ScheduleKey extends ValueObject {

    String value();

}
