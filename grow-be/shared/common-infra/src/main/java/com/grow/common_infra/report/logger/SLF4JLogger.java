package com.grow.common_infra.report.logger;

import com.grow.common_core.report.logger.Loggable;
import com.grow.common_infra.report.logger.SentryTagKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class SLF4JLogger implements Loggable {
    private final Environment environment;

    public void error(Object object) {
        log.error(object.toString());
    }

    public void alert(Object object) {
        log.error(object.toString(), SentryTagKey.NOTIFY.key(), constructAdditionalData());
    }

    public void warning(Object object) {
        log.warn(object.toString());
    }

    public void info(Object object) {
        log.info(object.toString());
    }

    public void logTrace(Exception ex) {
        error(String.format("Error message: %s", ex.getMessage()));
        Throwable cause = ex.getCause();
        if (cause != null) {
            error(String.format("Error cause message: %s", cause.getMessage()));
        }
        if (isLocalEnvironment()) {
            ex.printStackTrace();
        } else {
            error(ex.getStackTrace());
        }
    }

    private boolean isLocalEnvironment() {
        return environment.acceptsProfiles(Profiles.of("custom", "local"));
    }

    private Map<String, String> constructAdditionalData() {
        Map<String, String> additionalData = new HashMap<>();
//        additionalData.put("actionTime", LocalDateTime.now(ZoneId.of("UTC")).toString());
//        authUserContextHolder.getUserContext().ifPresent(userContext ->
//                additionalData.put("userContext", userContext.toString()));

        return additionalData;
    }
}