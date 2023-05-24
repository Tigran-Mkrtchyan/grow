package com.grow.common_core.report.logger;

public interface Loggable {
    void error(Object object);

    void alert(Object object);

    void warning(Object object);

    void info(Object object);

    void logTrace(Exception ex);
}