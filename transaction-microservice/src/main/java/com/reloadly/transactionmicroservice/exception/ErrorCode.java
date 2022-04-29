package com.reloadly.transactionmicroservice.exception;

public enum ErrorCode {
    ACCOUNT_NOT_FOUND(100, "Account not found"),
    INVALID_INPUT_PARAMS(100, "Invalid input parameters");
    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
