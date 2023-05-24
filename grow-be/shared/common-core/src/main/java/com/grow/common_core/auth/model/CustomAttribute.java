package com.grow.common_core.auth.model;

public enum CustomAttribute {
    SUB("sub"),
    PHONE("phone_number"),
    EMAIL("email"),
    USER_TYPE("custom:userType");

    public final String key;

    CustomAttribute(String key) {
        this.key = key;
    }
}
