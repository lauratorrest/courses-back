package com.company.coursya.shared.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCode {

    NOT_THE_SAME_EMAIL("ERR-001", "Correos diferentes"),
    EMAIL_ALREADY_EXISTS("ERR-002", "El correo ya se encuentra registrado"),
    NOT_REGISTERED("ERR-003", "No tenemos registrado ese correo."),
    WRONG_PASSWORD("ERR-004", "Contraseña equivocada."),
    USER_NOT_FOUND("ERR-005", "No hay ningún usuario asociado al correo.");

    private final String code;
    private final String message;
}
