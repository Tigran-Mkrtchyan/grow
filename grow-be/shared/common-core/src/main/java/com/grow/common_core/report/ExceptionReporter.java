package com.grow.common_core.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExceptionReporter {
    private static Reportable reporter;

    public static void reportException(Throwable exception) {
        reporter.report(exception);
    }

    @Autowired
    private void init(Reportable reporter) {
        ExceptionReporter.reporter = reporter;
    }
}