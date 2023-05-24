package com.grow.common_infra.dto;

import java.util.Map;

public record SignupUser(String username, String password, Map<String, String> customAttributes) {

    public SignupUser(String username, String password, Map<String, String> customAttributes) {
        this.username = username;
        this.password = password;
        this.customAttributes = customAttributes;
    }
}
