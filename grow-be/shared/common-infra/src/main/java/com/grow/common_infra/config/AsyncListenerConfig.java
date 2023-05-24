package com.grow.common_infra.config;

import com.grow.common_core.report.logger.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@ConditionalOnProperty(name = "async.enabled", havingValue = "true")
public class AsyncListenerConfig extends AsyncConfigurerSupport {
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

    @Bean
    @Override
    public Executor getAsyncExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    private static class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            String message = String.format("Problem occurred in async event listener %s, cause: %s",
                    method.getName(),
                    throwable.getMessage());
            Logger.alert(message);
        }
    }
}
