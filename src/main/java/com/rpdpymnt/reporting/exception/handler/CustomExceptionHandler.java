package com.rpdpymnt.reporting.exception.handler;

import com.rpdpymnt.reporting.domain.ErrorResponse;
import com.rpdpymnt.reporting.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;

/**
 * Responsible with business exception handling, only handle business/functional exceptions.
 *
 * Extends {@link BaseExceptionHandler}
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(SignatureException.class)
    protected ResponseEntity handleSignatureException(SignatureException ex) {
        log.error("Wrong amount, {}", ex);
        final ErrorResponse response = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity handleExpiredJwtException(ExpiredJwtException ex) {
        log.error("Wrong amount, {}", ex);
        final ErrorResponse response = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error("Wrong amount, {}", ex);
        final ErrorResponse response = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalAmountException.class)
    protected ResponseEntity handleIllegalAmountException(IllegalAmountException ex) {
        log.error("Wrong amount, {}", ex);
        final ErrorResponse response = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    protected ResponseEntity handleInsufficientFundsException(InsufficientFundsException ex) {
        log.error("Insufficient funds, {}", ex);
        final ErrorResponse response = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    protected ResponseEntity handleTransactionNotFoundException(TransactionNotFoundException ex) {
        log.error("Transaction not found, {}", ex);
        final ErrorResponse response = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PageNotFoundException.class)
    protected ResponseEntity handlePageNotFoundException(PageNotFoundException ex) {
        log.error("Page not found, {}", ex);
        final ErrorResponse response = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    protected ResponseEntity handleAccountException(InvalidTransactionException ex) {
        log.error("Progress failed, {}", ex);
        final ErrorResponse response = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}