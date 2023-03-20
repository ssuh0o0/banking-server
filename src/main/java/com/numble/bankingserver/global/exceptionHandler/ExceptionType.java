package com.numble.bankingserver.global.exceptionHandler;

import org.springframework.http.HttpStatus;

public interface ExceptionType {
    String getErrorCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
