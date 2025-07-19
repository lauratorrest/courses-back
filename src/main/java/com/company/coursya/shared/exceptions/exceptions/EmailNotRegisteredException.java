package com.company.coursya.shared.exceptions.exceptions;

import com.company.coursya.shared.exceptions.BaseException;
import com.company.coursya.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class EmailNotRegisteredException extends BaseException {

    public EmailNotRegisteredException(ExceptionCode exceptionCode) {
        super(HttpStatus.NOT_FOUND, exceptionCode.getCode(), exceptionCode.getMessage());
    }
}
