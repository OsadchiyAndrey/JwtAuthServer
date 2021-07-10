package com.osadchiy.rentservice.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends BaseException {

    {
        httpStatus = HttpStatus.FORBIDDEN;
    }

    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
