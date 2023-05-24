package com.grow.common_infra.event.publisher;

import com.grow.common_core.event.publisher.DomainEventPublisher;
import com.grow.common_core.event.publisher.IntegrationEventPublisher;
import com.grow.kernel.domain.base.DomainEvent;
import com.grow.kernel.domain.base.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InMemoryEventPublisher implements DomainEventPublisher, IntegrationEventPublisher {

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void publish(@NonNull DomainEvent event) {
        eventPublisher.publishEvent(event);
    }

    @Override
    public void publish(@NonNull IntegrationEvent event) {
        eventPublisher.publishEvent(event);
    }

}
