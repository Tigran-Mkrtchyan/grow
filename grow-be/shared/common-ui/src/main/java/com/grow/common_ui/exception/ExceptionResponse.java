package com.grow.common_ui.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}