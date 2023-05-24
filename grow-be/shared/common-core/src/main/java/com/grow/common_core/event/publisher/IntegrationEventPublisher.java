package com.grow.common_core.event.publisher;

import com.grow.kernel.domain.base.IntegrationEvent;
import lombok.NonNull;

public interface IntegrationEventPublisher {

    void publish(@NonNull IntegrationEvent event);

}
