package com.grow.common_ui.middleware;

import an.awesome.pipelinr.Command;
import com.grow.common_core.report.logger.Logger;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoggingMiddleware implements Command.Middleware {

    @Order(1)
    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        Logger.info(command);
        return next.invoke();
    }
}