package com.grow.common_ui.exception;

public class CommandValidationException extends RuntimeException {
    public CommandValidationException(String message) {
        super(message);
    }
    public CommandValidationException() {
    }
}
