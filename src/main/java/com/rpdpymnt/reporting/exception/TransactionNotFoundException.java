package com.rpdpymnt.reporting.exception;

import com.rpdpymnt.reporting.util.FunctionalErrorCode;

/**
 * Thrown when account doesn't exists.
 * Takes {@link FunctionalErrorCode} in custom constructor
 */
public class TransactionNotFoundException extends Exception {

    private FunctionalErrorCode functionalErrorCode;

    public TransactionNotFoundException() {
        super("Transaction not found.");
    }

    public TransactionNotFoundException(FunctionalErrorCode errorCode) {
        super(errorCode.toString());
        this.functionalErrorCode = errorCode;
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }
}