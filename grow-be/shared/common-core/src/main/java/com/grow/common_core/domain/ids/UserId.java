package com.grow.common_core.domain.ids;


import com.grow.kernel.domain.base.DomainObjectId;

public class UserId extends DomainObjectId {
    UserId() {
    }

    public UserId(String uuid) {
        super(uuid);
    }

    public UserId(DomainObjectId id) {
        super(id);
    }

}
