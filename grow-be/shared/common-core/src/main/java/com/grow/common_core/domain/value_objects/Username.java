package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;
import lombok.experimental.Accessors;

import java.util.Objects;

@Accessors(fluent = true)
public class Username implements ValueObject {

    private String value;

    public Username(String value) {
        if (EmailAddress.isValid(value)) {
            this.setUsername(new EmailAddress(value));
        } else if (PhoneNumber.isValid(value)) {
            this.setUsername(new PhoneNumber(value));
        } else {
            throw new IllegalStateException("Username must be either valid email or phone");
        }
    }

    public Username(PhoneNumber phone) {
        this.setUsername(phone);
    }

    public Username(EmailAddress email) {
        this.setUsername(email);
    }

    private void setUsername(EmailAddress email) {
        if (email == null) {
            throw new IllegalStateException("Username must not be null");
        }
        this.value = email.getValue();
    }

    private void setUsername(PhoneNumber phone) {
        if (phone == null) {
            throw new IllegalStateException("Username must not be null");
        }
        this.value = phone.getValue();
    }

    public String value() {
        return this.value;
    }

    public boolean equals(PhoneNumber phone) {
        if (phone == null) return false;
        return this.value.equals(phone.getValue());
    }

    public boolean equals(EmailAddress email) {
        if (email == null) return false;
        return this.value.equals(email.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return Objects.equals(value, username1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
