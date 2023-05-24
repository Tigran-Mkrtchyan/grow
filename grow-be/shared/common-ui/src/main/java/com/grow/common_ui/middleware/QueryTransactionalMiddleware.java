package com.grow.common_ui.middleware;

import an.awesome.pipelinr.Command;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class QueryTransactionalMiddleware implements Command.Middleware {
    @Order(3)
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public <R, C extends Command<R>> R invoke(C command, Command.Middleware.Next<R> next) {
        return next.invoke();
    }
}
