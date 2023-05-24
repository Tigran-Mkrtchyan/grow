package com.grow.common_ui.auth;

import com.grow.common_core.environment.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiKeyAuthorization {

    Profile[] allowedProfiles() default {Profile.PROD, Profile.STAGE, Profile.DEV, Profile.CUSTOM, Profile.LOCAL};

}