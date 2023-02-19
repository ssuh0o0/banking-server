package com.numble.bankingserver.global.response;

public enum ResponseStatus {

    // Success
    OK(200, "OK"),

    // Fail
    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");

    private int statusCode;
    private String code;

    ResponseStatus(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
