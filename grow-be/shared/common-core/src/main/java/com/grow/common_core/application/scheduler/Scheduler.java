package com.grow.common_core.application.scheduler;

import com.grow.common_core.application.scheduler.context.Context;
import com.grow.common_core.application.scheduler.exception.ScheduleKeyAlreadyExists;
import com.grow.common_core.application.scheduler.exception.ScheduleKeyNotFound;
import com.grow.common_core.application.scheduler.job.Job;
import com.grow.common_core.application.scheduler.key.ScheduleKey;
import com.grow.common_core.application.scheduler.periodicity.Periodicity;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public interface Scheduler {

    /**
     * Schedule job to run with specified periodicity
     *
     * @throws ScheduleKeyAlreadyExists in case there is already scheduled job with provided key
     * @see {@link ScheduleKey} how to design schedule keys
     */
    <T extends Job<C>, C extends Context> void schedule(@NonNull Class<T> jobClass, @NonNull Periodicity periodicity,
                                                        @NonNull C context, @NonNull ScheduleKey key)
            throws ScheduleKeyAlreadyExists;

    /**
     * Schedule job to run with specified periodicity
     *
     * @throws ScheduleKeyAlreadyExists in case there is already scheduled job found with provided key
     * @see {@link ScheduleKey} how to design schedule keys
     */
    <T extends Job<C>, C extends Context> void schedule(@NonNull Class<T> jobClass, @NonNull Periodicity periodicity,
                                                        @NonNull C context);

    /**
     * Schedule job to run with specified periodicities
     *
     * @throws ScheduleKeyAlreadyExists in case there is already scheduled job with provided key
     * @see {@link ScheduleKey} how to design schedule keys
     */
    <T extends Job<C>, C extends Context> void schedule(@NonNull Class<T> jobClass, @NonNull List<Periodicity> periodicities,
                                                        @NonNull C context, @NonNull ScheduleKey key);

    /**
     * Replace scheduled job to run with new periodicity
     *
     * @throws ScheduleKeyNotFound in case there is no scheduled job found with provided key
     */
    void reschedule(@NonNull ScheduleKey key, @NonNull Periodicity newPeriodicity)
            throws ScheduleKeyNotFound;

    /**
     * Replace scheduled job to run with new periodicities
     *
     * @throws ScheduleKeyNotFound in case there is no scheduled job found with provided key
     */
    void reschedule(@NonNull ScheduleKey key, @NonNull List<Periodicity> periodicities);

    /**
     * Unschedule scheduled job
     * <p>
     * Ignore in case there is no job found with provided key
     */
    void unschedule(@NonNull ScheduleKey key);

    /**
     * Check if scheduled job exists with specified key
     */
    boolean exists(@NonNull ScheduleKey key);

    /**
     * @return Next nearest execution time of job scheduled with key in UTC time zone
     * null in case job will not fire again
     */
    @Nullable
    LocalDateTime nextExecutionTime(@NonNull ScheduleKey key);

}
