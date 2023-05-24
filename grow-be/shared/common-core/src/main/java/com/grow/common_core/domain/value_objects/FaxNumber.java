package com.grow.common_core.domain.value_objects;


import com.grow.kernel.domain.base.ValueObject;

public class FaxNumber implements ValueObject {

    private String value;

    public FaxNumber(String value) {
        this.value = value;
    }

    protected FaxNumber() {
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
