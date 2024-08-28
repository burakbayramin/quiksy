package com.burak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PurchaseException extends RuntimeException{
    public PurchaseException(String message) {
        super(message);
    }
}
