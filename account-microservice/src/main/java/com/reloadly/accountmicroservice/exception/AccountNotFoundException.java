package com.reloadly.accountmicroservice.exception;

public class AccountNotFoundException extends AccountingBaseException {
    public AccountNotFoundException() {
        super(ErrorCode.ACCOUNT_NOT_FOUND);
    }
}
