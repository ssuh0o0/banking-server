package com.numble.bankingserver.global.exceptionHandler;

import lombok.Getter;

@Getter
public class BasicException extends RuntimeException{

    private final ExceptionType exceptionType;

    public BasicException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}