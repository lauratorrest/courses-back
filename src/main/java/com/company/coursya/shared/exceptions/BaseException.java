package com.company.coursya.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class BaseException extends RuntimeException {

    private final HttpStatus status;
    private final String code;
    private final String message;
    private final LocalDate date;

    public BaseException(HttpStatus status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
        this.date = LocalDate.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public LocalDate getDate() {
        return date;
    }
}
