package com.company.coursya.shared.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String statusValue;
    private String code;
    private String message;
    private String date;

    public ErrorResponse(HttpStatus status, String code, String message, String date) {
        this.status = status.value();
        this.statusValue = status.getReasonPhrase();
        this.code = code;
        this.message = message;
        this.date = date;
    }
}
