package com.grow.common_infra.report.logger;

public enum SentryTagKey {
    NOTIFY("notify"),
    USER_CONTEXT("userContext");

    SentryTagKey(String key) {
        this.key = key;
    }

    private final String key;

    public String key() {
        return key;
    }
}
