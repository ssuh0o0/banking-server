package com.numble.bankingserver.global.exception;

import com.numble.bankingserver.global.exceptionHandler.ExceptionType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserException implements ExceptionType {


    NOT_FOUND_MEMBER("NOT_FOUND_MEMBER", "회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_MEMBER("DUPLICATE_MEMBER", "중복된 회원 입니다.", HttpStatus.BAD_REQUEST),
    NOT_EQUAL_PASSWORD("NOT_EQUAL_PASSWORD", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ROLE("NOT_FOUND_ROLE", "사용자의 권한을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_EQUAL_ID("NOT_EQUAL_ID", "아이디가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    LOGOUT_MEMBER("LOGOUT_MEMBER", "로그아웃된 회원입니다.", HttpStatus.BAD_REQUEST);

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

    UserException(String errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }
}
