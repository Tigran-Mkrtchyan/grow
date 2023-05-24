package com.grow.common_infra.descriptor.id;

import com.grow.common_core.domain.ids.UserId;
import com.grow.common_core.domain.ids.WorkshopId;
import com.grow.common_infra.basictype.DomainObjectIdCustomType;
import com.grow.common_infra.descriptor.DomainObjectIdTypeDescriptor;

public class WorkshopIdType extends DomainObjectIdCustomType<WorkshopId> {

    private static final DomainObjectIdTypeDescriptor<WorkshopId> TYPE_DESCRIPTOR =
            new DomainObjectIdTypeDescriptor<>(WorkshopId.class, WorkshopId::new);

    public WorkshopIdType() {
        super(TYPE_DESCRIPTOR);
    }

}
