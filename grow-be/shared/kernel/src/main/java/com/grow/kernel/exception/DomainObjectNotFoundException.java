package com.grow.kernel.exception;

import com.grow.kernel.domain.base.DomainObjectId;

public class DomainObjectNotFoundException extends RuntimeException {

    public DomainObjectNotFoundException(DomainObjectId objectId) {
        super(String.format("Unable to find resource with specified %s", objectId.toString()));
    }

    public DomainObjectNotFoundException() {
        super("Unable to find resource.");
    }

    public DomainObjectNotFoundException(String message) {
        super("Unable to find resource." + message);
    }

}
