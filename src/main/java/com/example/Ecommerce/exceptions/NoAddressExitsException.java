package com.example.Ecommerce.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoAddressExitsException extends Exception{

    public NoAddressExitsException(String message) {
        super(message);
    }
}
