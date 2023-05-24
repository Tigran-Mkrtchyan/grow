package com.grow.common_core.domain.ids;


import com.grow.kernel.domain.base.DomainObjectId;

public class WorkshopId extends DomainObjectId {
    WorkshopId() {
    }

    public WorkshopId(String uuid) {
        super(uuid);
    }

    public WorkshopId(DomainObjectId id) {
        super(id);
    }

}
