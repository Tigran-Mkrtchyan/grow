package com.grow.common_ui.config;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.grow.common_ui.middleware.CommandTransactionalMiddleware;
import com.grow.common_ui.middleware.QueryTransactionalMiddleware;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelineConfiguration {

    @Bean
    Pipeline commandPipeline(ObjectProvider<Command.Handler> commandHandlers,
                             ObjectProvider<Notification.Handler> notificationHandlers,
                             ObjectProvider<Command.Middleware> middlewares) {
        return new Pipelinr()
                .with(commandHandlers::stream)
                .with(notificationHandlers::stream)
                .with(() -> middlewares.stream()
                        .filter((middleware -> !(middleware instanceof QueryTransactionalMiddleware))));
    }

    @Bean
    Pipeline queryPipeline(ObjectProvider<Command.Handler> commandHandlers,
                           ObjectProvider<Notification.Handler> notificationHandlers,
                           ObjectProvider<Command.Middleware> middlewares) {
        return new Pipelinr()
                .with(commandHandlers::stream)
                .with(notificationHandlers::stream)
                .with(() -> middlewares
                        .stream().filter(middleware -> !(middleware instanceof CommandTransactionalMiddleware)));
    }

}

