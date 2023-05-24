package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;
import lombok.Getter;

@Getter
public class Password implements ValueObject {
    private static final String PASSWORD_STRENGTH_REGEX = "^\\S{6,}$";

    private String password;

    public Password(String number) {
        this.setPassword(number);
    }

    private void setPassword(String password) {
        if (!Password.isValid(password)) {
            throw new IllegalStateException("Password is weak");
        }
        this.password = password;
    }

    private static boolean isValid(String password) {
        if (password == null) {
            return false;
        }
        return password.matches(PASSWORD_STRENGTH_REGEX);
    }

    @Override
    public String toString() {
        return password;
    }
}
