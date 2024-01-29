package com.rpdpymnt.reporting.exception;


import com.rpdpymnt.reporting.util.FunctionalErrorCode;

/**
 * Thrown when account doesn't has sufficient funds available
 * Takes {@link FunctionalErrorCode} in constructor to point specific point.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Insufficient funds");
    }
    public InsufficientFundsException(FunctionalErrorCode errorCode) {
        super(errorCode.toString());
    }
    public InsufficientFundsException(String message){super(message);}
}