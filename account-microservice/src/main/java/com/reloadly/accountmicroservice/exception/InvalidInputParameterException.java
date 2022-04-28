package com.reloadly.accountmicroservice.exception;

import static com.reloadly.accountmicroservice.exception.ErrorCode.INVALID_INPUT_PARAMS;

public class InvalidInputParameterException extends AccountingBaseException{
    public InvalidInputParameterException() {
        super(INVALID_INPUT_PARAMS);
    }
}
