package com.grow.workshop_core.domain.aggregate;

import com.grow.common_core.domain.ids.UserId;
import com.grow.common_core.domain.ids.WorkshopId;
import com.grow.common_core.domain.value_objects.EmailAddress;
import com.grow.common_core.domain.value_objects.Name;
import com.grow.common_core.domain.value_objects.Text;
import com.grow.kernel.domain.base.AbstractAggregateRoot;
import com.grow.kernel.domain.base.DomainObjectId;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public class WorkshopAggregate extends AbstractAggregateRoot<WorkshopId> {

    private UserId reporterId;
    private Text title;
    private Name hostName;
    private EmailAddress hostEmail;

    protected WorkshopAggregate() {
    }

    public WorkshopAggregate(Text title) {
        super(DomainObjectId.randomId(WorkshopId.class));
        this.title = title;
    }
}
