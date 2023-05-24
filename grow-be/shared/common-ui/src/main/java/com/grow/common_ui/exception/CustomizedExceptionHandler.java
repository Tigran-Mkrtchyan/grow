
package com.grow.common_ui.exception;

import com.grow.common_core.exception.AccessDeniedException;
import com.grow.kernel.domain.exception.DomainObjectException;
import com.grow.common_core.report.logger.Logger;
import com.grow.kernel.exception.BadRequestException;
import com.grow.kernel.exception.DomainObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static com.grow.common_core.report.ExceptionReporter.reportException;

@AllArgsConstructor
@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        Logger.logTrace(ex);
        reportException(ex);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DomainObjectNotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundExceptions(DomainObjectNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        Logger.warning(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<Object> handleNotFoundExceptions(UnauthorizedException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestExceptions(BadRequestException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        Logger.warning(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainObjectException.class)
    public final ResponseEntity<Object> handleDomainObjectExceptionExceptions(DomainObjectException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable cause = ex;
        if (ex.contains(CommandValidationException.class)) {
            do {
                cause = cause.getCause();
            }
            while (!(cause instanceof CommandValidationException));
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), cause.getMessage(), request.getDescription(false));
        Logger.warning(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BeanInstantiationException.class)
    public final ResponseEntity<Object> handleBeanInstantiationExceptionExceptions(BeanInstantiationException ex, WebRequest request) {
        Throwable cause = ex;
        ExceptionResponse exceptionResponse;
        if (ex.contains(CommandValidationException.class)) {
            do {
                cause = cause.getCause();
            }
            while (!(cause instanceof CommandValidationException));
            exceptionResponse = new ExceptionResponse(new Date(),
                    cause.getMessage(),
                    request.getDescription(false));
            Logger.warning(ex.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }

        exceptionResponse = new ExceptionResponse(new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        Logger.warning(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

}
