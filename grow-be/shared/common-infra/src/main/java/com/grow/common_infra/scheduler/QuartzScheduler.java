package com.grow.common_infra.scheduler;

import com.grow.common_core.application.scheduler.Scheduler;
import com.grow.common_core.application.scheduler.context.Context;
import com.grow.common_core.application.scheduler.exception.ScheduleException;
import com.grow.common_core.application.scheduler.exception.ScheduleKeyAlreadyExists;
import com.grow.common_core.application.scheduler.exception.ScheduleKeyNotFound;
import com.grow.common_core.application.scheduler.job.Job;
import com.grow.common_core.application.scheduler.key.ScheduleKey;
import com.grow.common_core.application.scheduler.periodicity.Periodicity;
import com.grow.common_core.util.DateTimeUtil;
import com.grow.common_infra.scheduler.util.QuartzUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component("QuartzSchedulerImpl")
@AllArgsConstructor
public class QuartzScheduler implements Scheduler {

    public static final String JOB_CLASS = "jobClass";
    public static final String JOB_CONTEXT = "jobContext";
    public static final String USER_CONTEXT = "userContext";
    private final org.quartz.Scheduler quartzScheduler;
    private final ApplicationContext applicationContext;

    @Override
    public <T extends Job<C>, C extends Context> void schedule(@NonNull Class<T> jobClass, @NonNull Periodicity periodicity,
                                                               @NonNull C context, @NonNull ScheduleKey key) {
        scheduleInternal(jobClass, List.of(periodicity), context, key);
    }


    @Override
    public <T extends Job<C>, C extends Context> void schedule(@NonNull Class<T> jobClass, @NonNull Periodicity periodicity,
                                                               @NonNull C context) throws ScheduleKeyAlreadyExists {
        scheduleInternal(jobClass, List.of(periodicity), context, (ScheduleKey) () -> UUID.randomUUID().toString());
    }

    @Override
    public <T extends Job<C>, C extends Context> void schedule(@NonNull Class<T> jobClass, @NonNull List<Periodicity> periodicities,
                                                               @NonNull C context, @NonNull ScheduleKey key) {
        scheduleInternal(jobClass, periodicities, context, key);
    }

    @Override
    public void reschedule(@NonNull ScheduleKey key, @NonNull Periodicity periodicity) {
        reschedule(key, List.of(periodicity));
    }

    private <T extends Job<C>, C extends Context> void scheduleInternal(@NonNull Class<T> jobClass, @NonNull List<Periodicity> periodicities,
                                                                        @NonNull C context, @NonNull ScheduleKey key) {
        try {
            String jobKey = key.value();
            ensureScheduledJobNotPresentWith(jobKey);
            QuartzJobBeanAdapter jobAdapter = new QuartzJobBeanAdapter();
            Map<String, Object> jobData = new HashMap<>();
            jobData.put(JOB_CLASS, jobClass);
            jobData.put(JOB_CONTEXT, context);

            JobDetail jobDetail = QuartzUtil.JobBuilder.build(jobAdapter.getClass(), jobData,
                    jobKey);
            for (int iteration = 0; iteration < periodicities.size(); iteration++) {
                Periodicity periodicity = periodicities.get(iteration);

                if (iteration < 1) {
                    Trigger trigger = QuartzUtil.TriggerBuilder.build(periodicity, constructTriggerName(jobKey, iteration));
                    quartzScheduler.scheduleJob(jobDetail, trigger);
                } else {
                    quartzScheduler.scheduleJob(QuartzUtil.TriggerBuilder
                            .build(jobDetail, periodicity, constructTriggerName(jobKey, iteration)));
                }
            }

        } catch (ScheduleKeyAlreadyExists e) {
            throw e;
        } catch (Exception e) {
            String message = "Exception during '%s' job schedule with cause '%s'";
            String jobName = jobClass.getCanonicalName();
            throw new ScheduleException(String.format(message, jobName, e.getMessage()));
        }
    }

    @Override
    public void reschedule(@NonNull ScheduleKey key, @NonNull List<Periodicity> periodicities) {
        String keyValue = key.value();
        try {
            JobKey jobKey = new JobKey(keyValue);
            if (!quartzScheduler.checkExists(jobKey))
                throw new ScheduleKeyNotFound(String.format(
                        "Unable to find schedule key with value '%s'", keyValue));
            List<? extends Trigger> triggersOfJob = quartzScheduler.getTriggersOfJob(jobKey).stream()
                    .sorted(Comparator.comparing(Trigger::getKey))
                    .collect(Collectors.toList());
            int newPeriodicityCount = periodicities.size();
            int existingTriggersCount = triggersOfJob.size();
            int iteration = 0;
            if (newPeriodicityCount <= existingTriggersCount) {
                for (Trigger trigger : triggersOfJob) {
                    if (iteration < newPeriodicityCount) {
                        Periodicity periodicity = periodicities.get(iteration);
                        Trigger newTrigger = QuartzUtil.TriggerBuilder.build(periodicity, trigger.getKey().getName());
                        quartzScheduler.rescheduleJob(trigger.getKey(), newTrigger);
                    } else {
                        quartzScheduler.unscheduleJob(trigger.getKey());
                    }
                    iteration++;
                }
            } else {
                JobDetail jobDetail = quartzScheduler.getJobDetail(jobKey);
                for (Periodicity periodicity : periodicities) {
                    if (iteration < existingTriggersCount) {
                        Trigger oldTrigger = triggersOfJob.get(iteration);
                        Trigger newTrigger = QuartzUtil.TriggerBuilder.build(periodicity, oldTrigger.getKey().getName());
                        quartzScheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
                    } else {
                        quartzScheduler.scheduleJob(QuartzUtil.TriggerBuilder
                                .build(jobDetail, periodicity, constructTriggerName(keyValue, iteration)));
                    }
                    iteration++;
                }
            }
        } catch (ScheduleKeyNotFound e) {
            throw e;
        } catch (Exception e) {
            String message = "Exception during job reschedule with key '%s' and cause '%s'";
            throw new ScheduleException(String.format(message, keyValue, e.getMessage()));
        }
    }

    @Override
    public void unschedule(@NonNull ScheduleKey key) {
        try {
            JobKey jobKey = new JobKey(key.value());
            if (!quartzScheduler.checkExists(jobKey)) return;

            for (Trigger trigger : quartzScheduler.getTriggersOfJob(jobKey)) {
                quartzScheduler.unscheduleJob(trigger.getKey());
            }
        } catch (ScheduleKeyNotFound e) {
            throw e;
        } catch (Exception e) {
            String message = "Exception during job unschedule with key '%s' and cause '%s'";
            throw new ScheduleException(String.format(message, key.value(), e.getMessage()));
        }
    }

    @Override
    public boolean exists(@NonNull ScheduleKey key) {
        try {
            return quartzScheduler.checkExists(new JobKey(key.value()));
        } catch (Exception e) {
            String message = "Exception during check exists with key '%s' and cause '%s'";
            throw new ScheduleException(String.format(message, key.value(), e.getMessage()));
        }
    }

    @Override
    public LocalDateTime nextExecutionTime(@NonNull ScheduleKey key) {
        try {
            List<? extends Trigger> triggers = Objects.requireNonNullElseGet(quartzScheduler.getTriggersOfJob(new JobKey(key.value())), () -> new ArrayList<Trigger>())
                    .stream().filter(Trigger::mayFireAgain)
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(triggers)) return null;

            Date nearestExecutionTime = triggers.stream()
                    .min(Comparator.comparing(Trigger::getNextFireTime))
                    .map(Trigger::getNextFireTime)
                    .orElseThrow(() -> new RuntimeException(String.format("Min next fire time does not exists for not empty triggers: '%s'", triggers)));
            return DateTimeUtil.toLocalDateTime(nearestExecutionTime.getTime());
        } catch (Exception e) {
            String message = "Unable to get next execution time of key '%s'.Cause '%s'";
            throw new ScheduleException(String.format(message, key.value(), e.getMessage()));
        }
    }

    private void ensureScheduledJobNotPresentWith(@NonNull String key) {
        try {
            if (quartzScheduler.checkExists(new JobKey(key)))
                throw new ScheduleKeyAlreadyExists(String.format(
                        "Already exists schedule key with value '%s'", key));
        } catch (ScheduleKeyAlreadyExists e) {
            throw e;
        } catch (Exception e) {
            throw new ScheduleException("Exception during ensureScheduledJobNotPresentWith with cause " + e.getMessage());
        }
    }

    private String constructTriggerName(@NonNull String jobKey, int triggerNumber) {
        return String.format("trigger_%s_%s", triggerNumber, jobKey);
    }

    @SuppressWarnings("all")
    private class QuartzJobBeanAdapter extends QuartzJobBean {

        @Override
        protected void executeInternal(JobExecutionContext quartzCtx) {
            Class<Job> jobClass = null;
            try {

                JobDataMap jobDataMap = quartzCtx.getJobDetail().getJobDataMap();
                jobClass = QuartzUtil.retrieveProperty(jobDataMap, JOB_CLASS,
                        Class.class);
                Context context = QuartzUtil.retrieveProperty(jobDataMap, JOB_CONTEXT,
                        Context.class);
                Job job = applicationContext.getBean(jobClass);
                job.execute(context);
            } catch (Exception e) {
                String message = "Execution of job '%s' failed with cause '%s'";
                String jobName = jobClass != null ? jobClass.getCanonicalName() : null;
                throw new RuntimeException(String.format(message, jobName, e.getMessage()));
            }
        }

    }

}
