package com.grow.common_core.report.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Logger {
    private static Loggable logger;

    public static void error(Object object) {
        logger.error(object);
    }

    public static void alert(Object object) {
        logger.alert(object);
    }

    public static void warning(Object object) {
        logger.warning(object);
    }

    public static void info(Object object) {
        logger.info(object);
    }

    public static void logTrace(Exception ex) {
        logger.logTrace(ex);
    }

    @Autowired
    private void init(Loggable logger) {
        Logger.logger = logger;
    }
}