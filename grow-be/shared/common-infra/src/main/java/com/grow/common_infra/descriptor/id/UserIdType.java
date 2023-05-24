package com.grow.common_infra.descriptor.id;

import com.grow.common_core.domain.ids.UserId;
import com.grow.common_infra.basictype.DomainObjectIdCustomType;
import com.grow.common_infra.descriptor.DomainObjectIdTypeDescriptor;

public class UserIdType extends DomainObjectIdCustomType<UserId> {

    private static final DomainObjectIdTypeDescriptor<UserId> TYPE_DESCRIPTOR =
            new DomainObjectIdTypeDescriptor<>(UserId.class, UserId::new);

    public UserIdType() {
        super(TYPE_DESCRIPTOR);
    }

}
