package com.rpdpymnt.reporting.exception;


import com.rpdpymnt.reporting.util.FunctionalErrorCode;

/**
 * Thrown if amount value or itself is wrong.
 * Takes {@link FunctionalErrorCode} information to point actual cause.
 */
public class IllegalAmountException extends RuntimeException {
    public IllegalAmountException() {
        super("Illegal Amount");
    }

    public IllegalAmountException(FunctionalErrorCode errorCode) {
        super(errorCode.toString());
    }
    public IllegalAmountException(String message){super(message);}
}