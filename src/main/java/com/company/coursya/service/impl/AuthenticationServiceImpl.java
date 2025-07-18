package com.company.coursya.service.impl;

import com.company.coursya.api.dto.authentication.PreRegisterResponse;
import com.company.coursya.model.AuthenticationData;
import com.company.coursya.model.User;
import com.company.coursya.repository.AuthenticationRepository;
import com.company.coursya.service.AuthenticationService;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.exceptions.ExceptionCode;
import com.company.coursya.shared.exceptions.exceptions.EmailAlreadyRegisteredException;
import com.company.coursya.shared.exceptions.exceptions.NotTheSameEmailException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final UserService userService;

    @Override
    public PreRegisterResponse savePreRegisterData(String email, String confirmEmail, String fullName) {
        if (!Objects.equals(email, confirmEmail)) {
            throw new NotTheSameEmailException(ExceptionCode.NOT_THE_SAME_EMAIL);
        }

        Boolean emailAlreadyRegistered = authenticationRepository.existsByEmail(email);
        if (emailAlreadyRegistered) {
            throw new EmailAlreadyRegisteredException(ExceptionCode.EMAIL_ALREADY_EXISTS);
        }

        AuthenticationData authData = authenticationRepository.save(
                AuthenticationData.builder()
                        .email(email)
                        .active(Boolean.FALSE)
                        .createdDate(ZonedDateTime.now(ZoneId.of("America/Bogota")).toLocalDateTime())
                        .build());
        User savedUser = userService.saveUser(fullName);

        return PreRegisterResponse.builder()
                .email(authData.getEmail())
                .fullName(savedUser.getFullName())
                .build();
    }
}
