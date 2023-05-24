package com.grow.common_infra.auth.model;

public enum CognitoClaim {

    TEMPORARY_PASSWORD("custom:temporary_password");
    private final String key;

    CognitoClaim(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }

}
