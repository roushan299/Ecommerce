package com.example.Ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoCartItemExitsException extends Exception{
    public NoCartItemExitsException(String message) {
        super(message);
    }
}
