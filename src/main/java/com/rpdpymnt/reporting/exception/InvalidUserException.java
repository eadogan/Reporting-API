package com.rpdpymnt.reporting.exception;

import com.rpdpymnt.reporting.util.FunctionalErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base exception where the rest of the exception under exception package extends.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidUserException extends RuntimeException {

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String s, InvalidUserException e) {
    }
}