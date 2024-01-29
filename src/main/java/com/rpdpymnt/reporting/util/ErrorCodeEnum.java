package com.rpdpymnt.reporting.util;

public enum ErrorCodeEnum {

    DONOTHONOR("Do not honor"),INVALIDTRANSACTION("Invalid Transaction"),INVALIDCARD("Invalid Card"),
    NOTSUFFICENTFUNDS("Not Sufficient Fund"),INCORRECTPIN("Incorrect PIN"),
    INVALIDCOUNTRYASSOCIATION("Invalid country association"),CURRENCYNOTALLOWED("Currency not allowed"),
    THREEDSECURETRANSPORTERROR("3-D Secure Transport Error"),
    TRANSACTIONNOTPERMITTEDTOCARDHOLDER("Transaction not permitted to cardholder");
    ErrorCodeEnum(String val) {
    }
}
