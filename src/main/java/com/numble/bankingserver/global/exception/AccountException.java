package com.numble.bankingserver.global.exception;

import com.numble.bankingserver.global.exceptionHandler.ExceptionType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AccountException implements ExceptionType {

    NOT_FRIEND("NOT_FRIEND", "해당 사용자와 거래할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NO_MONEY("LACK_MONEY", "잔액이 부족합니다.", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND("NOT_FOUND_ACCOUNT", "계좌를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    UNAUTHORIZED_PASSWORD("NOT_EQUAL_PASSWORD", "비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);

    String errorCode;
    String message;
    HttpStatus status;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    AccountException(String errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }
}
