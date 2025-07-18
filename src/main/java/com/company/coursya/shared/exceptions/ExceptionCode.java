package com.company.coursya.shared.exceptions;

public enum ExceptionCode {

    NOT_THE_SAME_EMAIL("ERR-001", "Correos diferentes"),
    EMAIL_ALREADY_EXISTS("ERR-002", "El correo ya se encuentra registrado");

    private final String code;
    private final String message;

    ExceptionCode(String code, String type) {
        this.code = code;
        this.message = type;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
