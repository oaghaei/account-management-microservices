package com.reloadly.transactionmicroservice.exception;

public class AccountingBaseException extends RuntimeException {
    private final ErrorCode errorCode;

    public AccountingBaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
