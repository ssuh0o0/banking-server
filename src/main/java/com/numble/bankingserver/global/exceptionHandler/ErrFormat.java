package com.numble.bankingserver.global.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrFormat {

    private String code;
    private String message;

    static ErrFormat create(ExceptionType exceptionType){
        return new ErrFormat(exceptionType.getErrorCode(), exceptionType.getMessage());
    }
}
