package com.grow.common_ui.auth.aspects;

import com.grow.common_ui.auth.ApiKeyAuthorization;
import com.grow.common_ui.exception.UnauthorizedException;
import com.grow.common_ui.config.ApplicationConfig;
import com.grow.common_core.environment.Profile;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Configuration
@AllArgsConstructor
public class ApiKeyAuthorizationAspectExecutor {

    private static final String API_KEY_HEADER = "x-api-key";
    private static final String PROD = "production";
    private final HttpServletRequest request;
    private final ApplicationConfig applicationConfig;
    private final Environment environment;

    @Around("@annotation(com.grow.common_ui.auth.ApiKeyAuthorization)")
    public Object authorizationCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        String header = request.getHeader(API_KEY_HEADER);

        if (header == null || !header.equals(applicationConfig.apiKey())) {
            throw new UnauthorizedException("Unauthorized api call");
        }
        Set<Profile> allowedProfiles = collectAllowedProfiles(joinPoint);
        if (!containsAll(allowedProfiles, environment.getActiveProfiles()))
            throw new UnauthorizedException("Unauthorized api call");

        return joinPoint.proceed();
    }

    @Around("@annotation(com.grow.common_ui.auth.AutomationApiKeyAuthorization)")
    public Object authorizationAutomationCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        String header = request.getHeader(API_KEY_HEADER);
        String[] activeProfiles = environment.getActiveProfiles();
        if (Arrays.asList(activeProfiles).contains(PROD)) {
            throw new UnauthorizedException("Unauthorized api call");
        }

        if (header == null || !header.equals(applicationConfig.apiKey())) {
            throw new UnauthorizedException("Unauthorized api call");
        }
        return joinPoint.proceed();
    }

    private boolean containsAll(Set<Profile> allowedProfiles, String[] activeProfiles) {
        if (CollectionUtils.isEmpty(allowedProfiles)) return false;

        return allowedProfiles.stream()
                .map(Profile::getName).collect(Collectors.toSet())
                .containsAll(Arrays.asList(activeProfiles));
    }

    private Set<Profile> collectAllowedProfiles(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiKeyAuthorization annotation = method.getAnnotation(ApiKeyAuthorization.class);
        return Set.of(annotation.allowedProfiles());
    }

}