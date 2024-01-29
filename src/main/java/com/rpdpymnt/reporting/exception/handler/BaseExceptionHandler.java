package com.rpdpymnt.reporting.exception.handler;

import com.rpdpymnt.reporting.domain.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Responsible for common exception handling can cause by any reason across framework.
 *
 * Extends {@link ResponseEntityExceptionHandler}
 */
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity handleIllegalExceptions(RuntimeException ex, WebRequest request) {
        final ErrorResponse errorResponse= new ErrorResponse(false, ex.getMessage());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}