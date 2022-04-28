package com.reloadly.accountmicroservice.exception.handler;

import com.reloadly.accountmicroservice.exception.AccountingBaseException;
import com.reloadly.api.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.time.format.DateTimeParseException;
import java.util.Date;

@RestControllerAdvice
public class RestApiExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(AccountingBaseException.class)
    public final ResponseEntity<ErrorDto> mancalaBaseExceptionHandler(final AccountingBaseException ex,
                                                                      final HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorDto().message(ex.getErrorCode().getMessage()).
                code(ex.getErrorCode().getCode()).
                uri(URI.create(request.getRequestURL().toString())).
                dateTime(new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,
            DateTimeParseException.class,
            ConstraintViolationException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            BindException.class})
    public ResponseEntity<ErrorDto> inputParameterExceptionHandler(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorDto().message("Malformed syntax of the request params").
                code(HttpStatus.BAD_REQUEST.value()).
                uri(URI.create(request.getRequestURL().toString())).
                dateTime(new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> generalExceptionHandler(Exception ex, HttpServletRequest request) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorDto().message("Internal Server Error").
                uri(URI.create(request.getRequestURL().toString())).
                code(HttpStatus.INTERNAL_SERVER_ERROR.value()).
                dateTime(new Date()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
