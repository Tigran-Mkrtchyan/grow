package com.grow.common_core.environment;

public enum Profile {

    PROD("production"),
    DEV("development"),
    STAGE("stage"),
    CUSTOM("custom"),
    TEST("test"),
    LOCAL("local");

    private final String name;

    Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
