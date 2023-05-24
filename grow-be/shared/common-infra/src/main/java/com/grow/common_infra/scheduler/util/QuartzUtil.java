package com.grow.common_infra.scheduler.util;

import com.grow.common_core.application.scheduler.periodicity.Cron;
import com.grow.common_core.application.scheduler.periodicity.OneTime;
import com.grow.common_core.application.scheduler.periodicity.Periodicity;
import com.grow.common_core.application.scheduler.periodicity.Repeatable;
import com.grow.common_core.util.DateTimeUtil;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

@SuppressWarnings("all")
public class QuartzUtil {

    public static <T> T retrieveProperty(JobDataMap dataMap, String propertyName,
                                         Class<T> type) {
        return findProperty(dataMap, propertyName, type)
                .orElseThrow(() -> new RuntimeException(String.format(
                        "Missing '%s' property in data map", propertyName)));
    }

    public static <T> Optional<T> findProperty(JobDataMap dataMap, String propertyName,
                                               Class<T> type) {
        Object property = dataMap.get(propertyName);
        if (property == null)
            return Optional.empty();
        try {
            return Optional.of((T) property);
        } catch (ClassCastException e) {
            String excpetedType = type.getCanonicalName();
            String actualType = property.getClass().getCanonicalName();
            throw new RuntimeException(String.format(
                    "Expected property '%s'to be of type '%s' of but found '%s'", propertyName, excpetedType, actualType));
        }
    }


    public static class JobBuilder {

        public static JobDetail build(Class<? extends org.quartz.Job> jobClass, Map<String, Object> jobData,
                                      String jobIdentity) {
            return org.quartz.JobBuilder.newJob()
                    .ofType(jobClass)
                    .withIdentity(jobIdentity)
                    .usingJobData(new JobDataMap(jobData))
                    .requestRecovery(true)
                    .build();
        }

    }

    public static class TriggerBuilder {

        public static Trigger build(Periodicity periodicity, String name) {
            org.quartz.TriggerBuilder triggerBuilder = constructBuilder(periodicity);
            return triggerBuilder.withIdentity(name).build();
        }

        public static Trigger build(JobDetail jobDetail, Periodicity periodicity, String name) {
            org.quartz.TriggerBuilder triggerBuilder = constructBuilder(periodicity);
            return triggerBuilder
                    .forJob(jobDetail)
                    .withIdentity(name).build();
        }

        private static org.quartz.TriggerBuilder constructBuilder(Periodicity periodicity) {
            if (periodicity instanceof OneTime) {
                return build((OneTime) periodicity);
            } else if (periodicity instanceof Repeatable) {
                return build((Repeatable) periodicity);
            } else if (periodicity instanceof Cron) {
                return build((Cron) periodicity, periodicity.startTime().getZone());
            } else {
                throw new UnsupportedOperationException(String.format(
                        "Unhandled type '%s' of periodicity to build quartz trigger", periodicity.getClass()));
            }
        }

        private static org.quartz.TriggerBuilder build(OneTime oneTime) {
            return org.quartz.TriggerBuilder.newTrigger()
                    .startAt(DateTimeUtil.toDate(oneTime.startTime()))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withMisfireHandlingInstructionFireNow()
                    );
        }

        private static org.quartz.TriggerBuilder build(Repeatable repeatable) {
            var simpleScheduleBuilder = SimpleScheduleBuilder
                    .simpleSchedule()
                    .withIntervalInSeconds(repeatable.repeatIntervalInSeconds())
                    .withMisfireHandlingInstructionIgnoreMisfires();
            if (repeatable.repeatForever()) {
                simpleScheduleBuilder.repeatForever();
            } else {
                simpleScheduleBuilder.withRepeatCount(repeatable.repeatCount());
            }
            org.quartz.TriggerBuilder<SimpleTrigger> triggerBuilder =
                    org.quartz.TriggerBuilder.newTrigger()
                            .withSchedule(simpleScheduleBuilder)
                            .startAt(DateTimeUtil.toDate(repeatable.startTime()));
            if (!repeatable.indefinite()) {
                triggerBuilder.endAt(DateTimeUtil.toDate(repeatable.endTime()));
            }
            return triggerBuilder;
        }

        private static org.quartz.TriggerBuilder build(Cron cron, ZoneId zoneId) {
            String expression = cron.expression().toString();
            if (!CronExpression.isValidExpression(expression)) {
                throw new UnsupportedOperationException(String.format(
                        "Provided cron expression '%s' is not valid", expression));
            }
            return org.quartz.TriggerBuilder.newTrigger()
                    .withSchedule(CronScheduleBuilder.cronSchedule(expression)
                            .withMisfireHandlingInstructionIgnoreMisfires()
                            .inTimeZone(TimeZone.getTimeZone(zoneId))
                    )
                    .startAt(DateTimeUtil.toDate(cron.startTime()));
        }

    }

}
