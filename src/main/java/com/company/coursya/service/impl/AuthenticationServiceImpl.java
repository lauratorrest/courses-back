package com.company.coursya.service.impl;

import com.company.coursya.api.dto.authentication.RegisterResponse;
import com.company.coursya.model.AuthenticationData;
import com.company.coursya.model.User;
import com.company.coursya.repository.AuthenticationRepository;
import com.company.coursya.service.AuthenticationService;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.exceptions.ExceptionCode;
import com.company.coursya.shared.exceptions.exceptions.EmailAlreadyRegisteredException;
import com.company.coursya.shared.exceptions.exceptions.NotTheSameEmailException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse savePreRegisterData(String email, String confirmEmail, String fullName, String password) {
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
                        .password(passwordEncoder.encode(password))
                        .createdDate(ZonedDateTime.now(ZoneId.of("America/Bogota")).toLocalDateTime())
                        .build());
        User savedUser = userService.saveUser(fullName);
        return RegisterResponse.builder()
                .email(authData.getEmail())
                .fullName(savedUser.getFullName())
                .build();
    }
}
