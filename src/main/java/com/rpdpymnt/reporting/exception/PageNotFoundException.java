package com.rpdpymnt.reporting.exception;

import com.rpdpymnt.reporting.util.FunctionalErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base exception where the rest of the exception under exception package extends.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PageNotFoundException extends Exception {

    public PageNotFoundException(FunctionalErrorCode errorCode) {
        super(errorCode.toString());
    }
    public PageNotFoundException(String message) {
        super(message);
    }
}