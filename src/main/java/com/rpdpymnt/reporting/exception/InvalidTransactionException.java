package com.rpdpymnt.reporting.exception;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException() {
        super("Invalid transaction request.");
    }

    public InvalidTransactionException(String message) {
        super(message);
    }

    public InvalidTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}