package com.grow.common_core.event.publisher;

import com.grow.kernel.domain.base.DomainEvent;
import lombok.NonNull;

public interface DomainEventPublisher {

    void publish(@NonNull DomainEvent event);

}
