package com.grow.common_core.auth.model;

public enum ClientMetadataKey {
    NAME("name"),
    USER_TYPE("userType");

    public final String key;

    ClientMetadataKey(String key) {
        this.key = key;
    }
}
