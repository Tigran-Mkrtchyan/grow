package com.grow.common_core.domain.value_objects;

import com.grow.kernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Accessors(fluent = true)
public class UserSub implements ValueObject {

    private String value;

    public UserSub(String value) {
        this.setValue(value);
    }

    private void setValue(String sub) {
        if (StringUtils.isBlank(sub)) {
            throw new IllegalStateException("UserSub value must not be null");
        }
        this.value = sub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSub userSub = (UserSub) o;
        return Objects.equals(value, userSub.value);
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
