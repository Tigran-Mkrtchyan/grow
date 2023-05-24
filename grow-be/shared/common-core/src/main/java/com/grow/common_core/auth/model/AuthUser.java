package com.grow.common_core.auth.model;

import com.grow.common_core.domain.value_objects.UserSub;
import lombok.NonNull;

import java.util.Arrays;

import static com.grow.common_core.auth.model.AuthUser.Status.FORCE_CHANGE_PASSWORD;
import static com.grow.kernel.domain.validation.Preconditions.requireNonNull;

public record AuthUser(UserSub sub, Status status, String temporaryPassword) {

    public AuthUser {
        requireNonNull(sub, new IllegalArgumentException("Sub must not be null"));
        requireNonNull(status, new IllegalArgumentException("Status must not be null/empty"));
    }

    public UserSub sub() {
        return this.sub;
    }

    public Status status() {
        return this.status;
    }

    public String temporaryPassword() {
        return this.temporaryPassword;
    }

    public boolean isInvited() {
        return FORCE_CHANGE_PASSWORD.equals(status);
    }

    public enum Status {
        CONFIRMED,
        UNCONFIRMED,
        FORCE_CHANGE_PASSWORD,
        ARCHIVED,
        COMPROMISED,
        EXTERNAL_PROVIDER,
        UNKNOWN,
        RESET_REQUIRED;

        public static Status fromString(@NonNull String userStatus) {
            return Arrays.stream(Status.values())
                    .filter(status -> status.name().equalsIgnoreCase(userStatus))
                    .findFirst()
                    .orElseThrow(() ->
                            new UnsupportedOperationException(String.format("Unhandled user status: %s ", userStatus)));
        }
    }
}
