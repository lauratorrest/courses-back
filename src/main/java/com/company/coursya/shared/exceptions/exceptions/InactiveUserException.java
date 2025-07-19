package com.company.coursya.shared.exceptions.exceptions;

import com.company.coursya.shared.exceptions.BaseException;
import com.company.coursya.shared.exceptions.ExceptionCode;
import org.springframework.http.HttpStatus;

public class InactiveUserException extends BaseException {
    public InactiveUserException(ExceptionCode exceptionCode) {
        super(HttpStatus.CONFLICT, exceptionCode.getCode(), exceptionCode.getMessage());
    }
}
