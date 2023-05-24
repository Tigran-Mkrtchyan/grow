package com.grow.common_infra.report;

import com.grow.common_infra.report.logger.SentryTagKey;
import com.grow.common_core.report.Reportable;
import io.sentry.Sentry;
import org.springframework.stereotype.Component;

@Component
public class SentryExceptionReporter implements Reportable {

    public SentryExceptionReporter() {
        Sentry.setTag(SentryTagKey.NOTIFY.key(), Boolean.TRUE.toString());
    }

    @Override
    public void report(Throwable exception) {
//        authUserContextHolder.getUserContext().ifPresent(userContext ->
//                Sentry.setExtra(USER_CONTEXT.key(), userContext.toString()));

        Sentry.captureException(exception);
    }

}