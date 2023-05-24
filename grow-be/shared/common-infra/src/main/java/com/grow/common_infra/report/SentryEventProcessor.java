package com.grow.common_infra.report;

import com.grow.common_infra.report.logger.SentryTagKey;
import io.sentry.EventProcessor;
import io.sentry.Hint;
import io.sentry.SentryEvent;
import io.sentry.protocol.Message;
import io.sentry.protocol.SentryException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SentryEventProcessor implements EventProcessor {

    public SentryEvent process(SentryEvent sentryEvent, Hint hint) {
        List<SentryException> exceptions = sentryEvent.getExceptions();
        Message message;
        if (exceptions == null &&
                (message = sentryEvent.getMessage()) != null &&
                isAlertLog(message.getParams())) {

            sentryEvent.setTag(SentryTagKey.NOTIFY.key(), Boolean.TRUE.toString());

            return sentryEvent;
        } else if (exceptions != null) {
            return sentryEvent;
        }
        return null;
    }

    private boolean isAlertLog(List<String> logParams) {
        if (logParams == null) return false;
        return logParams.stream()
                .anyMatch(param -> param.equals(SentryTagKey.NOTIFY.key()));
    }
}