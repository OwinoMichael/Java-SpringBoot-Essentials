package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;

public class ExternalCatFactDownException extends CustomBaseException{
    public ExternalCatFactDownException(HttpStatus status, SimpleResponse simpleResponse) {
        super(status, simpleResponse);
    }
}
